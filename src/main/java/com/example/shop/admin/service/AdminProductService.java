package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;
import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductFilterRequest;
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


    public List<ProductTO> getFilteredProducts(ProductFilterRequest filter) throws IllegalArgumentException{
        // 모든 필수값 검증
        if (filter.getMinQuantity() == null ||
                filter.getMinPrice() == null ||
                filter.getMaxPrice() == null) {
            throw new IllegalArgumentException("모든 필수값을 입력해야 합니다.");
        }

        return adminDAO.getProductByFilter(filter); // DAO 호출
    }
}





