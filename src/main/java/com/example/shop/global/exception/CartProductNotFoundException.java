package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class CartProductNotFoundException extends BusinessExceptionHandler {
    public CartProductNotFoundException() {
        super(ErrorCodes.CART_PRODUCT_NOT_FOUND);
    }
}
