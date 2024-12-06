package com.example.shop.user.mapper;

import com.example.shop.user.dto.ProductResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    Long countProducts();
    List<ProductResponse> getAllProducts(Map<String, Object> params);
    List<ProductResponse> getAllProductsWithoutPaging();
    ProductResponse getProductDetail(Long productId);
    List<ProductResponse> getProductByNameOrPrice(Map<String, Object> params);
}
