package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class OrderDetailNotFoundException extends BusinessExceptionHandler {
    public OrderDetailNotFoundException() {
        super(ErrorCodes.ORDER_DETAIL_NOT_FOUND);
    }
}
