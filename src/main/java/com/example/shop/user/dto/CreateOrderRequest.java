package com.example.shop.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateOrderRequest {
    @Valid
    @NotNull
    private DeliveryInfoRequest deliveryInfo;
}
