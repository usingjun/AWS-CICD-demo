package com.example.shop.user.controller;

import com.example.shop.common.dto.PageResponse;
import com.example.shop.user.dto.ProductResponse;
import com.example.shop.user.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResponse<ProductResponse> response = productService.getAllProducts(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductDetail(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> getProductByNameOrPrice(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "price", required = false) Long price) {
        return ResponseEntity.ok(productService.getProductByNameOrPrice(productName, price));
    }
}