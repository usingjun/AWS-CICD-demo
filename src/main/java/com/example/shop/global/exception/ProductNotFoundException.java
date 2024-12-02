package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductNotFoundException extends BusinessExceptionHandler {
    public ProductNotFoundException() {
        super(ErrorCodes.PRODUCT_NOT_FOUND);
    }
}
