package com.example.shop.user.controller;

import com.example.shop.common.dto.PageResponse;
import com.example.shop.user.dto.CreateOrderRequest;
import com.example.shop.common.dto.OrderListResponse;
import com.example.shop.common.dto.OrderResponse;
import com.example.shop.user.dto.UpdateOrderRequest;
import com.example.shop.user.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User-Order")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @Operation(summary = "주문 수정")
    @PutMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable String orderNumber, @RequestBody @Valid UpdateOrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(orderNumber, request));
    }

    @Operation(summary = "주문 목록 조회")
    @GetMapping
    public ResponseEntity<PageResponse<OrderListResponse>> getOrders(
            @RequestParam(defaultValue = "1") int page
            , @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.getOrders(page, size));
    }

    @Operation(summary = "주문 상세 조회")
    @GetMapping("/{orderNumber}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String orderNumber) {
        return ResponseEntity.ok(orderService.getOrder(orderNumber));
    }

    @Operation(summary = "주문 취소")
    @PostMapping("/{orderNumber}/cancel")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable String orderNumber) {
        return ResponseEntity.ok(orderService.cancelOrder(orderNumber));
    }

}
