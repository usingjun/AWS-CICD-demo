package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class NoBearer extends BusinessExceptionHandler {
    public NoBearer() {
        super(ErrorCodes.NO_BEARER);
    }

}
