package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class NotEnoughQuantityException extends BusinessExceptionHandler {
    public NotEnoughQuantityException() {
        super(ErrorCodes.NOT_ENOUGH_QUANTITY);
    }
}
