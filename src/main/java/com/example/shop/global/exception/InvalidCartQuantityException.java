package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class InvalidCartQuantityException extends BusinessExceptionHandler {
    public InvalidCartQuantityException() {
        super(ErrorCodes.INVALID_CART_QUANTITY);
    }
}
