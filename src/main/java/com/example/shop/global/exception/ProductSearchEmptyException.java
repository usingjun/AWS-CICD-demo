package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductSearchEmptyException extends BusinessExceptionHandler {
    public ProductSearchEmptyException() {
        super(ErrorCodes.PRODUCTS_SEARCH_EMPTY);
    }
}
