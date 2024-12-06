package com.example.shop.admin.dto;

import com.example.shop.common.dto.OrderResponse;
import com.example.shop.domain.order.Order;
import lombok.Getter;

@Getter
public class AdminOrderResponse extends OrderResponse {

    private final String userName;  // 주문자 이름
    private final String userEmail; // 주문자 이메일

    public AdminOrderResponse(Order order) {
        super(order);
        this.userName = order.getUser().getUserName();
        this.userEmail = order.getUser().getEmail();
    }
}
