package com.example.shop.domain.product;

import com.example.shop.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "products")
public class Product  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @NotNull
    @Column(name = "product_name")
    private String productName;
    @NotNull
    private int quantity;
    @NotNull
    private BigDecimal price;
    private String image;
    private String description;
}
