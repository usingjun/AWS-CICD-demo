package com.example.shop.user.controller;

import com.example.shop.common.dto.PageResponse;
import com.example.shop.user.dto.CreateOrderRequest;
import com.example.shop.common.dto.OrderListResponse;
import com.example.shop.common.dto.OrderResponse;
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
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @PutMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable String orderNumber, @RequestBody @Valid UpdateOrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(orderNumber, request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<OrderListResponse>> getOrders(
            @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.getOrders(page, size));
    }

    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String orderNumber) {
        return ResponseEntity.ok(orderService.getOrder(orderNumber));
    }

    @PostMapping("/{orderNumber}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable String orderNumber) {
        return ResponseEntity.ok(orderService.cancelOrder(orderNumber));
    }

}
