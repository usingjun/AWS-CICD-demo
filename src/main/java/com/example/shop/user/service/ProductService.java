package com.example.shop.user.service;

import com.example.shop.user.dao.UserDao;
import com.example.shop.user.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<ProductResponse> getProductDetail(Long productId) {
        return Optional.ofNullable(userDao.getProductDetail(productId));
    }

    public List<ProductResponse> getProductByNameOrPrice(String productName, Long price) {
        if (productName == null && price == null) {
            return userDao.getAllProducts();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("productName", productName);
        params.put("price", price);

        return userDao.getProductByNameOrPrice(params);
    }
}
