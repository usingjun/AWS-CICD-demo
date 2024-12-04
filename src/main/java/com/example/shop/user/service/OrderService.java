package com.example.shop.user.service;

import com.example.shop.admin.service.OrderAdminService;
import com.example.shop.domain.cart.CartDetail;
import com.example.shop.domain.cart.CartDetailRepository;
import com.example.shop.domain.order.DeliveryInfo;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderRepository;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductRepository;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.global.exception.EmptyCartException;
import com.example.shop.global.exception.ProductNotFoundException;
import com.example.shop.global.exception.UserNotFoundException;
import com.example.shop.global.util.SecurityUtil;
import com.example.shop.user.dto.CreateOrderRequest;
import com.example.shop.user.dto.CreateOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

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
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        // 유저 조회
        User user = getCurrentUser();

        // 유저 장바구니 조회
        List<CartDetail> cartDetails = cartDetailRepository.findByUserId(user.getId());
        if (cartDetails.isEmpty()) {
            throw new EmptyCartException();
        }

        // 주문 생성 및 user, request 매핑
        DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                .receiverPostalCode(request.getReceiverPostalCode())
                .receiverName(request.getReceiverName())
                .receiverAddress(request.getReceiverAddress())
                .receiverPhone(request.getReceiverPhone())
                .shippingMessage(request.getShippingMessage())
                .build();

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

        return new CreateOrderResponse(order);
    }
}
