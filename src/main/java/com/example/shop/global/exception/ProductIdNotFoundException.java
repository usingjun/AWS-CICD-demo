package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductIdNotFoundException extends BusinessExceptionHandler {
    public ProductIdNotFoundException() {
        super(ErrorCodes.PRODUCTID_NOT_FOUND);
    }
}
