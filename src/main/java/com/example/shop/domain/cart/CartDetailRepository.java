package com.example.shop.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query("SELECT c FROM CartDetail c JOIN FETCH c.product WHERE c.user.id = :userId")
    List<CartDetail> findByUserIdWithProduct(@Param("userId") Long userId);

    @Query("select c from CartDetail c join fetch c.product where c.user.id = :userId and c.product.id = :productId")
    Optional<CartDetail> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    void deleteByUserIdAndProductId(Long userId, Long productId);
}
