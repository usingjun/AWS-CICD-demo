package com.example.shop.user.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias(value = "productDetailResponse")
public class ProductDetailResponse {

    private Long productId;
    private String productName;
    private Long price;
    private String description;
    private String image;

}
