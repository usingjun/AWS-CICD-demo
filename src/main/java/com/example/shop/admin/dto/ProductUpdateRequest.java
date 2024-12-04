package com.example.shop.admin.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Setter
@Getter
public class ProductUpdateRequest {

    private Long productId;
    private Long quantity;
    private Long price;
    private String image;
    private String description;
    private LocalDateTime updatedAt;

}
