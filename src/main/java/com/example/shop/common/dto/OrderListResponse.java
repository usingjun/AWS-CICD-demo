package com.example.shop.common.dto;

import com.example.shop.domain.order.Order;
import com.example.shop.domain.order.OrderStatus;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class OrderListResponse {

    private final Long id;
    private final String orderNumber;
    private final BigDecimal totalPrice;
    private final OrderStatus status;
    private final LocalDateTime orderDate;
    private final LocalDateTime updatedDate;

    public OrderListResponse(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();
        this.totalPrice = order.getTotalPrice();
        this.status = order.getOrderStatus();
        this.orderDate = order.getCreatedAt();
        this.updatedDate = order.getUpdatedAt();
    }
}
