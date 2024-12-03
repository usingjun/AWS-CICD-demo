package com.example.shop.global.exception;


import com.example.shop.global.response.BusinessExceptionHandler;

public class NoAuthAccessTokenException extends BusinessExceptionHandler {
    public NoAuthAccessTokenException() {
        super(ErrorCodes.NO_AUTH_ACCESS_TOKEN);
    }
}
