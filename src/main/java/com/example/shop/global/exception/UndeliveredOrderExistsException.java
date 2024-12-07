package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class UndeliveredOrderExistsException extends BusinessExceptionHandler {
    public UndeliveredOrderExistsException() {super(ErrorCodes.UNDELIVERED_ORDER_EXISTS);}
}