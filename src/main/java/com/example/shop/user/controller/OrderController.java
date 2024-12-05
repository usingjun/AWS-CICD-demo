package com.example.shop.user.controller;

import com.example.shop.common.dto.PageResponse;
import com.example.shop.user.dto.CreateOrderRequest;
import com.example.shop.user.dto.OrderListResponse;
import com.example.shop.user.dto.OrderResponse;
import com.example.shop.user.dto.UpdateOrderRequest;
import com.example.shop.user.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<PageResponse<OrderListResponse>> getOrders(
            @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {
        PageResponse<OrderListResponse> response = orderService.getOrders(page, size);
        return ResponseEntity.ok(response);
    }

}
