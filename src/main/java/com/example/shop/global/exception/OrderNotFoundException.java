package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class OrderNotFoundException extends BusinessExceptionHandler {
    public OrderNotFoundException() {
        super(ErrorCodes.ORDER_NOT_FOUND);
    }
}
