package com.example.shop.admin.controller;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductFilterRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/products") // 디폴트 경로 설정
public class AdminProductController {

    private final AdminProductService adminProductService;

    // 메인 브랜치의 코드 (유지)
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

    // feature/#40-물품수정 브랜치에서 추가한 코드
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest productUpdateRequest) {

        productUpdateRequest.setProductId(id);
        String result = adminProductService.postProduct(productUpdateRequest);

        if ("정상적으로 입력되었습니다".equals(result)) {
            return ResponseEntity.ok(result); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result); // 400 Bad Request
        }
    }
    @PostMapping
    public ResponseEntity<String> insertProduct(@RequestBody ProductCreateRequest product) {
        String result = adminProductService.insertProduct(product);

        if ("정상적으로 입력되지 않았습니다".equals(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> getProductsByFilter(@ModelAttribute ProductFilterRequest productFilterRequest) {



        try {
            // Service 호출
            List<ProductTO> products = adminProductService.getFilteredProducts(productFilterRequest);
            return ResponseEntity.ok(products);
        } catch (IllegalArgumentException e) {
            // 예외 메시지를 클라이언트에 반환
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }

    }






}