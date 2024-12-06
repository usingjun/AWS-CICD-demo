package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductUpdateFailedException extends BusinessExceptionHandler {
    public ProductUpdateFailedException() {
        super(ErrorCodes.NOT_MODIFIABLE_PRODUCT);
    }
}
