package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class RefreshTokenExpiredException extends BusinessExceptionHandler {
    public RefreshTokenExpiredException() {super(ErrorCodes.REFRESH_TOKEN_EXPIRED);}
}
