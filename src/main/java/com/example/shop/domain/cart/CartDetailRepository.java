package com.example.shop.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Query("SELECT c FROM CartDetail c JOIN FETCH c.product WHERE c.user.id = :userId")
    List<CartDetail> findByUserIdWithProduct(@Param("userId") Long userId);
}
