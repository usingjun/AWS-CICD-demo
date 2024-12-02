package com.example.shop.global.response;

import com.example.shop.global.exception.ErrorCodes;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessExceptionHandler.class)
    public ErrorResponse handleCustomException(BusinessExceptionHandler e) {
        ErrorCodes error = e.getErrorCodes();
        return new ErrorResponse(error.getMessage(), error.getStatus().toString());
    }

}
