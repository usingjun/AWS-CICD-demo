package com.example.shop.user.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@RequiredArgsConstructor
@Alias(value = "productResponse")
public class ProductResponse {

    @NonNull private Long productId;
    @NonNull private String productName;
    @NonNull private Long price;
    private String description;
    @NonNull private String image;

}
