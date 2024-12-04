package com.example.shop.user.dto;

import com.example.shop.domain.order.DeliveryInfo;
import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderDetail;
import com.example.shop.domain.order.OrderStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {

    private final String orderNumber;        // 주문번호
    private final BigDecimal totalPrice;     // 최종 결제 금액
    private final OrderStatus orderStatus;   // 주문 상태
    private final LocalDateTime orderDate;   // 주문 일시
    private final List<CreateOrderDetailInfo> orderDetails;  // 주문 상품 정보
    private final DeliveryInfoResponse deliveryInfo; // 배송 정보

    public OrderResponse(Order order) {
        this.orderNumber = order.getOrderNumber();
        this.totalPrice = order.getTotalPrice();
        this.orderStatus = order.getOrderStatus();
        this.orderDate = order.getCreatedAt();
        this.orderDetails = order.getOrderDetails().stream()
                .map(CreateOrderDetailInfo::new)
                .toList();
        this.deliveryInfo = new DeliveryInfoResponse(order.getDeliveryInfo());
    }

    @Getter
    public static class DeliveryInfoResponse {
        private final String receiverAddress;
        private final String receiverName;
        private final String receiverPhone;
        private final String receiverPostalCode;
        private final String shippingMessage;

        public DeliveryInfoResponse(DeliveryInfo deliveryInfo) {
            this.receiverAddress = deliveryInfo.getReceiverAddress();
            this.receiverName = deliveryInfo.getReceiverName();
            this.receiverPhone = deliveryInfo.getReceiverPhone();
            this.receiverPostalCode = deliveryInfo.getReceiverPostalCode();
            this.shippingMessage = deliveryInfo.getShippingMessage();
        }
    }

    @Getter
    public static class CreateOrderDetailInfo {
        private final Long productId;        // 상품 ID
        private final String productName;    // 상품명
        private final int quantity;          // 주문 수량
        private final BigDecimal price;      // 상품 가격

        public CreateOrderDetailInfo(OrderDetail orderDetail) {
            this.productId = orderDetail.getProduct().getId();
            this.productName = orderDetail.getProduct().getProductName();
            this.quantity = orderDetail.getQuantity();
            this.price = orderDetail.getProduct().getPrice();
        }
    }
}
