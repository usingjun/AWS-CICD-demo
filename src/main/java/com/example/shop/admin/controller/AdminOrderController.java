package com.example.shop.admin.controller;

import com.example.shop.admin.dto.OrderDeliveryListRequest;
import com.example.shop.admin.service.AdminOrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @Operation(summary = "관리자 주문 배송 일괄 처리")
    @PatchMapping("/delivery")
    public ResponseEntity<Void> updateOrderStatusDelivery(@RequestBody @Valid OrderDeliveryListRequest orderDeliveryListRequest) {
        adminOrderService.updateOrderStatusDelivery(orderDeliveryListRequest.getOrderDeliveryRequests());
        return ResponseEntity.ok().build();
    }
}
