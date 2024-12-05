package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductsEmptyException extends BusinessExceptionHandler {
    public ProductsEmptyException() {
        super(ErrorCodes.PRODUCTS_EMPTY);
    }
}
