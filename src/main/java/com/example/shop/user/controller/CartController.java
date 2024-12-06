package com.example.shop.user.controller;

import com.example.shop.user.dto.AddCartProductRequest;
import com.example.shop.user.dto.CartDetailResponse;
import com.example.shop.user.dto.CartResponse;
import com.example.shop.user.dto.UpdateCartQuantityRequest;
import com.example.shop.user.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="User-Cart")
@RestController
@RequestMapping("/api/cart/products")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(summary = "장바구니에 물품 추가")
    @PostMapping
    public ResponseEntity<Void> addCartProduct(@RequestBody @Valid AddCartProductRequest request) {
        cartService.addCartProduct(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "장바구니에 담긴 물품 조회")
    @GetMapping
    public ResponseEntity<CartResponse> getCartDetails() {
        return ResponseEntity.ok(new CartResponse(cartService.getCartDetails()));
    }

    @Operation(summary = "장바구니에 담긴 특정 물품 삭제")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeCartProduct(@PathVariable("productId") Long productId) {
        cartService.removeCartProduct(productId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "장바구니에 담긴 물품의 수량 변경")
    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateCartQuantity(@PathVariable("productId") Long productId, @RequestBody @Valid UpdateCartQuantityRequest request) {
        cartService.updateCartQuantity(productId, request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "장바구니에 비우기")
    @DeleteMapping
    public ResponseEntity<Void> removeAllCartProducts() {
        cartService.removeAllCartProducts();
        return ResponseEntity.ok().build();
    }
}
