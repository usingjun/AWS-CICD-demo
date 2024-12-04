package com.example.shop.admin.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ProductFilterRequest {


    private int minQuantity;
    private int minPrice;
    private int maxPrice;
}
