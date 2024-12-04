package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class EmptyCartException extends BusinessExceptionHandler {
    public EmptyCartException() {
        super(ErrorCodes.EMPTY_CART);
    }
}
