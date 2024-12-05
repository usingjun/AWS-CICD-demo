package com.example.shop.user.controller;

import com.example.shop.user.dto.CreateOrderRequest;
import com.example.shop.user.dto.OrderResponse;
import com.example.shop.user.dto.UpdateOrderRequest;
import com.example.shop.user.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable String orderNumber, @RequestBody @Valid UpdateOrderRequest request) {
        OrderResponse response = orderService.updateOrder(orderNumber, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String orderNumber) {
        OrderResponse orderResponse = orderService.getOrder(orderNumber);
        return ResponseEntity.ok(orderResponse);
    }
}
