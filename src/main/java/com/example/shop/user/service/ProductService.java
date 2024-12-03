package com.example.shop.user.service;

import com.example.shop.user.dao.UserDao;
import com.example.shop.user.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final UserDao userDao;

    @Autowired
    public ProductService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<ProductResponse> getAllProducts() {
        return userDao.getAllProducts();
    }

    public ProductResponse getProductDetail(Long id) {
        return userDao.getProductDetail(id);
    }
}
