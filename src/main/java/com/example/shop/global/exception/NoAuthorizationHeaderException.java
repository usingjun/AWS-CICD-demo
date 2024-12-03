package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class NoAuthorizationHeaderException extends BusinessExceptionHandler {
    public NoAuthorizationHeaderException() {
        super(ErrorCodes.NO_AUTHORIZATION_HEADER);
    }
}
