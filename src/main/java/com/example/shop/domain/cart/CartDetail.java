package com.example.shop.domain.cart;

import com.example.shop.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "cart_details")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_detail_id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="product_id")
    private Long productId;

    @NotNull
    private Long quantity;
}
