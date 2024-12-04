package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class NotModifiableOrderException extends BusinessExceptionHandler {
    public NotModifiableOrderException() {
        super(ErrorCodes.NOT_MODIFIABLE_ORDER);
    }
}
