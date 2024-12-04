package com.example.shop.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;


@ToString
@Setter
@Getter

public class ProductTO {


    private Long productId;
    private String productName;
    private Long quantity;
    private Long price;
    private String image;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
