package com.example.shop.user.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CartDetailResponse {

    private long productId;
    private String productName;
    private BigDecimal productPrice;
    private int quantity;

    public CartDetailResponse(long productId, String productName, BigDecimal productPrice, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }
}
