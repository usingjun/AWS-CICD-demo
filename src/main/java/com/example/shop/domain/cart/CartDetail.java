package com.example.shop.domain.cart;

import com.example.shop.domain.BaseEntity;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.user.User;
import com.example.shop.global.exception.InvalidCartQuantityException;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    private int quantity;

    public CartDetail(User user, Product product, int quantity) {
        validateQuantity(quantity);
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    // 연관관계 편의 메서드
    public void setUser(User user) {
        this.user = user;
        user.getCartDetails().add(this);
    }

    public void changeQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new InvalidCartQuantityException();
        }
    }
}
