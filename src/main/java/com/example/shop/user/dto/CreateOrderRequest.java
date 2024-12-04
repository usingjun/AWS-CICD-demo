package com.example.shop.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateOrderRequest {

    @NotNull
    private String receiverAddress;

    @NotNull
    private String receiverName;

    @NotNull
    private String receiverPhone;

    @NotNull
    private String receiverPostalCode;

    private String shippingMessage;

    public CreateOrderRequest(String receiverAddress, String receiverName, String receiverPhone, String receiverPostalCode, String shippingMessage) {
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverPostalCode = receiverPostalCode;
        this.shippingMessage = shippingMessage;
    }
}
