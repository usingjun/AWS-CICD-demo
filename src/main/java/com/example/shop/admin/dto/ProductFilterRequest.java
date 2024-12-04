package com.example.shop.admin.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ProductFilterRequest {


    private Long minQuantity;
    private Long minPrice;
    private Long maxPrice;
}
