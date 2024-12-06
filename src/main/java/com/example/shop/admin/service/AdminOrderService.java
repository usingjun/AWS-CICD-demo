package com.example.shop.admin.service;

import com.example.shop.admin.dao.OrderDeliveryRepository;
import com.example.shop.admin.dto.AdminOrderListResponse;
import com.example.shop.admin.dto.AdminOrderResponse;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.admin.dto.OrderSearchRequest;
import com.example.shop.common.dto.PageResponse;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.order.OrderStatus;
import com.example.shop.global.exception.OrderNotFoundException;
import com.example.shop.global.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.shop.admin.service.OrderDeliveryUtil.orderDeliveryTemplate;
import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKeyToday;
import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKeyYesterday;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final EmailSender emailSender;
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final OrderRepository orderRepository;

    @Async("customTaskExecutor")
    public void sendDeliveryAlertEmail(OrderDeliveryRequest order) {
        String subject = "[DevCourse Team 7] 주문하신 상품이 배송이 시작됐습니다.";
        String content = orderDeliveryTemplate(order);

        emailSender.sendMail(order.getEmail(), subject, content);
    }

    @Transactional
    public void updateOrderStatusDelivery(List<OrderDeliveryRequest> orderDeliveryList) {
        orderDeliveryList.forEach(orderRequest -> {
            Order order = orderRepository.findById(orderRequest.getOrderId())
                    .orElseThrow(OrderNotFoundException::new);

            order.changeStatus(OrderStatus.SHIPPING);

            // 캐싱된 배송 대기 주문 데이터 삭제
            removeCachingOrder(orderRequest);
        });

        // 배송 알림 이메일 보내기
        orderDeliveryList.forEach(this::sendDeliveryAlertEmail);
    }

    public void cachingDeliveryOrder(String email, Long orderId) {
        orderDeliveryRepository.addOrderEmail(getCachingDeliveryOrderKey(), new OrderDeliveryRequest(email, orderId));
    }

    public void updateCachingOrder(String email, Long orderId) {
        OrderDeliveryRequest cachedOrder = new OrderDeliveryRequest(email, orderId);
        String key = getOrderKeyYesterday();
        if (!deliverableToday() && orderDeliveryRepository.existOrder(key, cachedOrder)) {
            orderDeliveryRepository.removeOrderEmail(key, cachedOrder);
            orderDeliveryRepository.addOrderEmail(getCachingDeliveryOrderKey(), cachedOrder);
        }
    }

    public void cancelCachingOrder(String email, Long orderId) {
        OrderDeliveryRequest cachedOrder = new OrderDeliveryRequest(email, orderId);
        removeCachingOrder(cachedOrder);
    }
    
    // 캐싱된 주문 데이터 Redis에서 삭제
    private void removeCachingOrder(OrderDeliveryRequest cachedOrder) {
        String key = getOrderKeyYesterday();

        if (orderDeliveryRepository.existOrder(key, cachedOrder)) {
            orderDeliveryRepository.removeOrderEmail(key, cachedOrder);
        } else {
            orderDeliveryRepository.removeOrderEmail(getOrderKeyToday(), cachedOrder);
        }
    }

    // 캐싱된 배송 예정 주문의 키값 가져오기
    private String getCachingDeliveryOrderKey() {
        if (deliverableToday()) {
            return getOrderKeyYesterday();
        }
        return getOrderKeyToday();
    }

    // 당일에 배송 가능 여부
    private boolean deliverableToday() {
        return LocalDateTime.now().getHour() < 14;
    }

    public AdminOrderResponse getOrder(String orderNumber) {

        Order order = orderRepository.findOrderAndOrderDetailAndUserAndProductByOrderNumber(orderNumber)
                .orElseThrow(OrderNotFoundException::new);

        return new AdminOrderResponse(order);
    }

    public PageResponse<AdminOrderListResponse> searchOrders(OrderSearchRequest request, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Order> orderPage = orderRepository.searchOrders(request, pageable);

        Page<AdminOrderListResponse> responsePage = orderPage.map(AdminOrderListResponse::new);
        return new PageResponse<>(responsePage);
    }
}
