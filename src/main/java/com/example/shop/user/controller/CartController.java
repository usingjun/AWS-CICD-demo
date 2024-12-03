package com.example.shop.user.controller;

import com.example.shop.user.dto.AddCartProductRequest;
import com.example.shop.user.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/products")
    public ResponseEntity<Void> addCartProduct(
            @AuthenticationPrincipal UserDetails userDetails
            , @RequestBody AddCartProductRequest request) {
        String userEmail = userDetails.getUsername();
        cartService.addCartProduct(userEmail, request);
        return ResponseEntity.ok().build();
    }
}
