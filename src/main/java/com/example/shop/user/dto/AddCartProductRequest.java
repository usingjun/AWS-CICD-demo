package com.example.shop.user.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddCartProductRequest {

    @NotNull(message = "상품을 선택해주세요.")
    private Long productId;

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
    private int quantity;

    public AddCartProductRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
