package com.example.shop.admin.controller;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductFilterRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.service.AdminProductService;
import com.example.shop.global.exception.DataInsertFailedException;
import com.example.shop.global.exception.ProductUpdateFailedException;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "전체 목록 조회")
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

    @Operation(summary = "물품 목록 수정")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProductById(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest productUpdateRequest) {


            productUpdateRequest.setProductId(id);
            adminProductService.postProduct(productUpdateRequest);

            return ResponseEntity.ok().build(); // 200 OK

    }


    @Operation(summary = "물품 생성")
    @PostMapping
    public ResponseEntity<Void> insertProduct(@RequestBody ProductCreateRequest product) {

            adminProductService.insertProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Operation(summary = "물품 필터링")
    @GetMapping("/filter")
    public ResponseEntity<Object> getProductsByFilter(@ModelAttribute ProductFilterRequest productFilterRequest) {


            // Service 호출
            List<ProductTO> products = adminProductService.getFilteredProducts(productFilterRequest);
            return ResponseEntity.ok(products);


    }

}
