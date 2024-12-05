package com.example.shop.admin.service;

import com.example.shop.admin.dao.OrderDeliveryRepository;
import com.example.shop.admin.dto.OrderDeliveryRequest;
import com.example.shop.global.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.shop.admin.service.OrderDeliveryUtil.orderDeliveryTemplate;
import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKeyToday;
import static com.example.shop.batch.util.OrderDeliveryBatchUtil.getOrderKeyYesterday;

@Service
@RequiredArgsConstructor
public class OrderAdminService {

    private final EmailSender emailSender;
    private final OrderDeliveryRepository orderDeliveryRepository;

    @Async("customTaskExecutor")
    public void sendDeliveryAlertEmail(OrderDeliveryRequest order) {
        String subject = "[DevCourse Team 7] 주문하신 상품이 배송이 시작됐습니다.";
        String content = orderDeliveryTemplate(order);

        emailSender.sendMail(order.getEmail(), subject, content);
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
}
