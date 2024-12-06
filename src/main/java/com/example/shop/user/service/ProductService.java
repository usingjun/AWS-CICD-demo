package com.example.shop.user.service;

import com.example.shop.common.dto.PageResponse;
import com.example.shop.global.exception.ProductIdNotFoundException;
import com.example.shop.global.exception.ProductSearchEmptyException;
import com.example.shop.global.exception.ProductsEmptyException;
import com.example.shop.user.dao.UserDao;
import com.example.shop.user.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final UserDao userDao;

    @Autowired
    public ProductService(UserDao userDao) {
        this.userDao = userDao;
    }

    public PageResponse<ProductResponse> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ProductResponse> products = userDao.getAllProducts(pageable);

        if (products.isEmpty()) {
            throw new ProductsEmptyException();
        }
        return new PageResponse<>(products);
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
            Page<ProductResponse> allProducts = userDao.getAllProducts(Pageable.unpaged());

            if (allProducts.isEmpty()) {
                throw new ProductsEmptyException();
            }
            return allProducts.getContent();
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
