package com.example.shop.admin.controller;



import com.example.shop.admin.dto.ProductTO;

import com.example.shop.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/products") //디폴트 경로 설정
public class AdminProductController {

    private final AdminProductService adminProductService;

    @GetMapping // 요청을 받으면 전체 리스트 출력
    public ResponseEntity<List<ProductTO>> getProducts() {
        List<ProductTO> lists = adminProductService.getAllProducts();

        if (lists.isEmpty()) {
            // 데이터가 없는 경우 204 No Content 상태 반환
            return ResponseEntity.noContent().build();
        }

        // 데이터가 있는 경우 200 OK 상태와 함께 반환
        return ResponseEntity.ok(lists);
    }

}





