package com.example.shop.user.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateOrderRequest {

    private UpdateDeliveryInfoRequest deliveryInfo;

    // 수정할 주문 상세 목록
    private List<UpdateOrderDetailRequest> orderDetails;

    @Getter
    @NoArgsConstructor
    public static class UpdateDeliveryInfoRequest {
        private String receiverAddress;
        private String receiverName;
        private String receiverPhone;
        private String receiverPostalCode;
        private String shippingMessage;
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateOrderDetailRequest {
        @NotNull
        private Long orderDetailId;  // 수정할 주문 상세 ID

        @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
        private int quantity;        // 변경할 수량
    }

}
