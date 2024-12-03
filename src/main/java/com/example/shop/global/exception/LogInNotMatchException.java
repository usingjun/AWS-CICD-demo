package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class LogInNotMatchException extends BusinessExceptionHandler {
    public LogInNotMatchException() {super(ErrorCodes.LOG_IN_NOT_MATCH);}
}
