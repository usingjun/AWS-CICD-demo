package com.example.shop.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddCartProductRequest {

    private Long productId;
    private int quantity;

    public AddCartProductRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
