package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class LogOutTokenException extends BusinessExceptionHandler {
    public LogOutTokenException() {super(ErrorCodes.LOG_OUT_TOKEN);}
}
