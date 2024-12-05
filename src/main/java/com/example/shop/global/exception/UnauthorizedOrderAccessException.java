package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class UnauthorizedOrderAccessException extends BusinessExceptionHandler {
    public UnauthorizedOrderAccessException() {
        super(ErrorCodes.UNAUTHORIZED_ORDER_ACCESS);
    }
}
