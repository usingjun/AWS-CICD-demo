package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class UserNotFound extends BusinessExceptionHandler {
    public UserNotFound() {
        super(ErrorCodes.MEMBER_NOT_FOUND);
    }
}
