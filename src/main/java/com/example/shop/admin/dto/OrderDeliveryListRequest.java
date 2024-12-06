package com.example.shop.admin.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDeliveryListRequest {

    @Valid
    @NotEmpty(message = "주문 배송 요청 목록은 비어 있을 수 없습니다.")
    private List<OrderDeliveryRequest> orderDeliveryRequests;
}
