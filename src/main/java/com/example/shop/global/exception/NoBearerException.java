package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class NoBearerException extends BusinessExceptionHandler {
    public NoBearerException() {
        super(ErrorCodes.NO_BEARER);
    }

}
