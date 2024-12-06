package com.example.shop.user.dao;

import com.example.shop.user.dto.ProductDetailResponse;
import com.example.shop.user.dto.ProductResponse;
import com.example.shop.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    private final UserMapper userMapper;

    @Autowired
    public UserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        if (pageable.isUnpaged()) {
            List<ProductResponse> products = userMapper.getAllProductsWithoutPaging();
            return new PageImpl<>(products, Pageable.unpaged(), products.size());
        }

        Map<String, Object> params = new HashMap<>();
        params.put("offset", pageable.getOffset());
        params.put("pageSize", pageable.getPageSize());

        List<ProductResponse> products = userMapper.getAllProducts(params);
        long total = countTotalProducts();

        return new PageImpl<>(products, pageable, total);
    }

    public ProductDetailResponse getProductDetail(Long productId) {
        return userMapper.getProductDetail(productId);
    }

    public List<ProductResponse> getProductByNameOrPrice(Map<String, Object> params) {
        return userMapper.getProductByNameOrPrice(params);
    }

    private long countTotalProducts() {
        return userMapper.countProducts();
    }
}
