package com.example.shop.user.service;

import com.example.shop.global.exception.ProductIdNotFoundException;
import com.example.shop.global.exception.ProductSearchEmptyException;
import com.example.shop.global.exception.ProductsEmptyException;
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
        List<ProductResponse> products = userDao.getAllProducts();
        if (products.isEmpty()) {
            throw new ProductsEmptyException();
        }
        return products;
    }

    public ProductResponse getProductDetail(Long productId) {
        ProductResponse product = userDao.getProductDetail(productId);
        if (product == null) {
            throw new ProductIdNotFoundException();
        }
        return product;
    }

    public List<ProductResponse> getProductByNameOrPrice(String productName, Long price) {
        if (productName == null && price == null) {
            return userDao.getAllProducts();
        }

        Map<String, Object> params = new HashMap<>();
        params.put("productName", productName);
        params.put("price", price);

        List<ProductResponse> products = userDao.getProductByNameOrPrice(params);
        if (products.isEmpty()) {
            throw new ProductSearchEmptyException();
        }
        return products;
    }
}
