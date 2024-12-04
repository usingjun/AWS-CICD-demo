package com.example.shop.admin.dao;

import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AdminDAO {

    private final AdminMapper adminMapper;

    // feature/#40-물품수정 브랜치에서 추가된 메서드
    public int updateProduct(ProductUpdateRequest productUpdateRequest) {
        return adminMapper.updateProduct(productUpdateRequest);
    }

    // main 브랜치에서 추가된 메서드
    public List<ProductTO> getAllProducts() {
        return adminMapper.selectAllProduct();
    }
}