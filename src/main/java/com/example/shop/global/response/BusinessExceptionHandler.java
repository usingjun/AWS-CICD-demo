package com.example.shop.global.response;


import com.example.shop.global.exception.ErrorCodes;
import lombok.Getter;

@Getter
public class BusinessExceptionHandler extends RuntimeException {
    private final ErrorCodes errorCodes;
    public BusinessExceptionHandler(ErrorCodes errorCodes) {
        super(errorCodes.getMessage());
        this.errorCodes = errorCodes;
    }

}
