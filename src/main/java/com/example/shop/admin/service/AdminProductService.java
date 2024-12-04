package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;
import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProductService {

    private final AdminDAO adminDAO;

    // feature/#40-물품수정 브랜치에서 추가된 메서드
    public String postProduct(ProductUpdateRequest productUpdateRequest) {
        int result = adminDAO.updateProduct(productUpdateRequest);
        if (result == 0) {
            return "정상적으로 입력되지 않았습니다";
        }
        return "정상적으로 입력되었습니다";
    }

    // main 브랜치에서 추가된 메서드
    public List<ProductTO> getAllProducts() {
        return adminDAO.getAllProducts();
    }


    public String insertProduct(ProductCreateRequest productCreateRequest) {
        int result = adminDAO.createProduct(productCreateRequest);
        if (result == 0) {
            return "정상적으로 입력되지 않았습니다";
        }
        return "정상적으로 입력되었습니다";
    }
}