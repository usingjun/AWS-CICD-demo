package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class EmptyOrderDetailException extends BusinessExceptionHandler {
    public EmptyOrderDetailException() {
        super(ErrorCodes.EMPTY_ORDER_DETAIL);
    }
}
