package com.example.shop.user.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse {

    private final List<CartDetailResponse> items;
    private final int totalCount;

    public CartResponse(List<CartDetailResponse> items) {
        this.items = items;
        this.totalCount = items.size();
    }
}
