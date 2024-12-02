package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class LogInNotMatch extends BusinessExceptionHandler {
    public LogInNotMatch() {super(ErrorCodes.LOG_IN_NOT_MATCH);}
}
