package com.example.shop.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias(value = "productResponse")
public class ProductResponse {

    private Long productId;
    private String productName;
    private Long price;
    private String image;

}
