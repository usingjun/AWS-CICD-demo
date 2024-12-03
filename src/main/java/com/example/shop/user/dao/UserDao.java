package com.example.shop.user.dao;

import com.example.shop.user.dto.ProductResponse;
import com.example.shop.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final UserMapper userMapper;

    @Autowired
    public UserDao(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<ProductResponse> getAllProducts() {
        return userMapper.getAllProducts();
    }

    public ProductResponse getProductDetail(Long id) {
        return userMapper.getProductDetail(id);
    }
}
