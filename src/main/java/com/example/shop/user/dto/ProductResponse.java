package com.example.shop.user.dto;

import lombok.Builder;
import lombok.Getter;
import org.apache.ibatis.type.Alias;

@Getter
@Builder
@Alias(value = "productResponse")
public class ProductResponse {

    private Long id;
    private String productName;
    private Long price;
    private String description;
    private String image;

}
