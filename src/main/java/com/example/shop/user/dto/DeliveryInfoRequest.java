package com.example.shop.user.dto;

import com.example.shop.domain.order.DeliveryInfo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryInfoRequest {
    @NotNull
    private String receiverAddress;

    @NotNull
    private String receiverName;

    @NotNull
    private String receiverPhone;

    @NotNull
    private String receiverPostalCode;

    // null 허용
    private String shippingMessage;

    // DeliveryInfo로 변환
    public DeliveryInfo toDeliveryInfo() {
        return DeliveryInfo.builder()
                .receiverAddress(receiverAddress)
                .receiverName(receiverName)
                .receiverPhone(receiverPhone)
                .receiverPostalCode(receiverPostalCode)
                .shippingMessage(shippingMessage)  // null 가능
                .build();
    }
}
