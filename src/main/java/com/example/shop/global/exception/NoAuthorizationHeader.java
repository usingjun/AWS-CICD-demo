package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class NoAuthorizationHeader extends BusinessExceptionHandler {
    public NoAuthorizationHeader() {
        super(ErrorCodes.NO_AUTHORIZATION_HEADER);
    }
}
