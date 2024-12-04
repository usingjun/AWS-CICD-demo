package com.example.shop.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryInfo {

    @Column(name = "receiver_postal_code")
    @NotNull
    private String receiverPostalCode;

    @Column(name = "receiver_name")
    @NotNull
    private String receiverName;

    @Column(name="receiver_address")
    @NotNull
    private String receiverAddress;

    @Column(name="receiver_phone")
    @NotNull
    private String receiverPhone;

    @Column(name = "shipping_message")
    private String shippingMessage;

    @Builder
    public DeliveryInfo(String receiverPostalCode, String receiverName, String receiverAddress, String receiverPhone, String shippingMessage) {
        this.receiverPostalCode = receiverPostalCode;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.shippingMessage = shippingMessage;
    }
}
