package com.example.shop.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@ToString
@Getter
@Setter
public class ProductCreateRequest {
    private String productName;
    private Long quantity;
    private Long price;
    private String image;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
