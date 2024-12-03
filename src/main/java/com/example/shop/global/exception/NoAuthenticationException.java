package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class NoAuthenticationException extends BusinessExceptionHandler {

    public NoAuthenticationException() {
        super(ErrorCodes.NO_AUTHENTICATION);
    }
}
