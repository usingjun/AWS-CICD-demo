package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class UserNotFoundException extends BusinessExceptionHandler {
    public UserNotFoundException() {
        super(ErrorCodes.MEMBER_NOT_FOUND);
    }
}
