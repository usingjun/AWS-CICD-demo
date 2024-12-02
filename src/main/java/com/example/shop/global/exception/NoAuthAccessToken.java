package com.example.shop.global.exception;


import com.example.shop.global.response.BusinessExceptionHandler;

public class NoAuthAccessToken extends BusinessExceptionHandler {
    public NoAuthAccessToken() {
        super(ErrorCodes.NO_AUTH_ACCESS_TOKEN);
    }
}
