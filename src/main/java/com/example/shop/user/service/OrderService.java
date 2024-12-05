package com.example.shop.user.service;

import com.example.shop.admin.service.OrderAdminService;
import com.example.shop.common.dto.PageResponse;
import com.example.shop.domain.cart.CartDetail;
import com.example.shop.domain.cart.CartDetailRepository;
import com.example.shop.domain.order.DeliveryInfo;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderDetail;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.global.exception.*;
import com.example.shop.global.util.SecurityUtil;
import com.example.shop.user.dto.CreateOrderRequest;
import com.example.shop.user.dto.OrderListResponse;
import com.example.shop.user.dto.OrderResponse;
import com.example.shop.user.dto.UpdateOrderRequest;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final EntityManager em;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final OrderAdminService orderAdminService;

    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        // 유저 조회
        User user = getCurrentUser();

        // 유저 장바구니 조회
        List<CartDetail> cartDetails = cartDetailRepository.findByUserId(user.getId());
        if (cartDetails.isEmpty()) {
            throw new EmptyCartException();
        }

        // 주문 생성 및 user, request 매핑
        DeliveryInfo deliveryInfo = request.getDeliveryInfo().toDeliveryInfo();

        Order order = Order.createOrder()
                .user(user)
                .deliveryInfo(deliveryInfo)
                .build();

        // 재고 검증, OrderDetail 생성
        for (CartDetail cartDetail : cartDetails) {
            // 비관적 락으로 재고 조회
            Product product = productRepository.findByIdWithPessimisticLock(cartDetail.getProduct().getId())
                    .orElseThrow(ProductNotFoundException::new);

            // 물품 수량 감소
            product.decreaseQuantity(cartDetail.getQuantity());
        }

        // OrderDetail 생성
        order.createOrderDetailsFromCart(cartDetails);

        Order createdOrder = orderRepository.save(order);
        cartDetailRepository.deleteAllByUserId(user.getId());
      
        orderAdminService.cachingDeliveryOrder(user.getEmail(), order.getId());

        return new OrderResponse(order);
    }

    @Transactional
    public OrderResponse updateOrder(String orderNumber, UpdateOrderRequest request) {
        // 유저 조회
        User user = getCurrentUser();

        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(OrderNotFoundException::new);

        // 주문자 본인 확인
        if (!order.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedOrderAccessException();
        }

        // 주문 상태 확인
        order.verifyUpdatable();

        // 배송 정보 수정
        if (request.getDeliveryInfo() != null) {
            order.updateDeliveryInfo(request.getDeliveryInfo());
        }

        // 수정할 주문 상세 정보 처리
        if (request.getOrderDetails() != null && !request.getOrderDetails().isEmpty()) {

            for (UpdateOrderRequest.UpdateOrderDetailRequest detailRequest : request.getOrderDetails()) {
                OrderDetail orderDetail = order.getOrderDetails().stream()
                        .filter(detail -> detail.getId().equals(detailRequest.getOrderDetailId()))
                        .findFirst()
                        .orElseThrow(OrderDetailNotFoundException::new);

                // 비관적 락으로 재고 조회
                Product product = productRepository.findByIdWithPessimisticLock(orderDetail.getProduct().getId())
                        .orElseThrow(ProductNotFoundException::new);

                // 수량 업데이트 (재고 증감 포함)
                orderDetail.updateQuantity(product, detailRequest.getQuantity());
            }

            // 총 금액 재계산
            order.updateTotalPrice();

            // 캐싱된 주문 수정
            orderAdminService.updateCachingOrder(user.getEmail(), order.getId());
        }

        return new OrderResponse(order);
    }

    public OrderResponse getOrder(String orderNumber) {
        // 유저 조회
        User user = getCurrentUser();

        Order order = orderRepository.findOrderAndOrderDetailByOrderNumber(orderNumber)
                .orElseThrow(OrderNotFoundException::new);

        // 주문자 본인 확인
        if (!order.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedOrderAccessException();
        }

        return new OrderResponse(order);
    }

    public PageResponse<OrderListResponse> getOrders(int page, int size) {
        // 유저 조회
        User user = getCurrentUser();
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Order> orders = orderRepository.findByUserId(user.getId(), pageable);
        Page<OrderListResponse> orderListResponse = orders.map(OrderListResponse::new);

        return new PageResponse<>(orderListResponse);
    }
}
