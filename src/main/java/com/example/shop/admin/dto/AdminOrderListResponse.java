package com.example.shop.admin.dto;

import com.example.shop.common.dto.OrderListResponse;
import com.example.shop.domain.order.Order;
import lombok.Getter;

@Getter
public class AdminOrderListResponse extends OrderListResponse {
    private final String userName;  // 주문자 이름

    public AdminOrderListResponse(Order order) {
        super(order);
        this.userName = order.getUser().getUserName();
    }
}
