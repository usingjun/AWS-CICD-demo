package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductIDModifiedNotFoundException extends BusinessExceptionHandler {
    public ProductIDModifiedNotFoundException() {
        super(ErrorCodes. NOT_INSERT_PRODUCT_ID);
    }
}
