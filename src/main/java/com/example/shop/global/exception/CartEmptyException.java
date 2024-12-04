package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class CartEmptyException extends BusinessExceptionHandler {
    public CartEmptyException() {
        super(ErrorCodes.CART_EMPTY);
    }
}
