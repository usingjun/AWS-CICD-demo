package com.example.shop.domain.cart;

import com.example.shop.domain.product.Product;
import com.example.shop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query("SELECT c FROM CartDetail c JOIN FETCH c.product WHERE c.user.id = :userId")
    List<CartDetail> findByUserIdWithProduct(@Param("userId") Long userId);

    @Query("select c from CartDetail c join fetch c.product where c.user = :user and c.product = :product")
    Optional<CartDetail> findByUserAndProduct(@Param("user") User user, @Param("product") Product product);
}
