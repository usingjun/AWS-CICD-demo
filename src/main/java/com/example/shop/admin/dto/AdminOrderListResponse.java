package com.example.shop.admin.dto;

import com.example.shop.common.dto.OrderListResponse;
import com.example.shop.domain.order.Order;
import lombok.Getter;

@Getter
public class AdminOrderListResponse extends OrderListResponse {
    private final String orderName;  // 주문자 이름
    private final String receiverName;  // 수령자 이름

    public AdminOrderListResponse(Order order) {
        super(order);
        this.orderName = order.getUser().getUserName();
        this.receiverName = order.getDeliveryInfo().getReceiverName();
    }
}
