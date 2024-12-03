package com.example.shop.user.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCartQuantityRequest {

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
    private int quantity;

    public UpdateCartQuantityRequest(int quantity) {
        this.quantity = quantity;
    }
}
