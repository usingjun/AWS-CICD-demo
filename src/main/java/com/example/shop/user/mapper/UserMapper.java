package com.example.shop.user.mapper;

import com.example.shop.user.dto.ProductResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<ProductResponse> getAllProducts();
    ProductResponse getProductDetail(Long id);
}
