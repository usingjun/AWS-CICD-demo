package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductFilterNotFoundException extends BusinessExceptionHandler {
    public ProductFilterNotFoundException() {
        super(ErrorCodes.NOT_INSERT_PRODUCT_Filter);
    }
}
