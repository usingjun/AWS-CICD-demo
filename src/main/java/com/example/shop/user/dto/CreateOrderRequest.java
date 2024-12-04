package com.example.shop.user.dto;

import com.example.shop.domain.order.DeliveryInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateOrderRequest {
    @Valid
    @NotNull
    private CreateDeliveryInfoRequest deliveryInfo;

    @Getter
    @NoArgsConstructor
    public static class CreateDeliveryInfoRequest {
        @NotNull
        private String receiverAddress;

        @NotNull
        private String receiverName;

        @NotNull
        private String receiverPhone;

        @NotNull
        private String receiverPostalCode;

        private String shippingMessage;

        // 엔티티의 DeliveryInfo로 변환
        public DeliveryInfo toDeliveryInfo() {
            return DeliveryInfo.builder()
                    .receiverAddress(receiverAddress)
                    .receiverName(receiverName)
                    .receiverPhone(receiverPhone)
                    .receiverPostalCode(receiverPostalCode)
                    .shippingMessage(shippingMessage)
                    .build();
        }
    }
}