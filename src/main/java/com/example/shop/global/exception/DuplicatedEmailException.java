package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class DuplicatedEmailException extends BusinessExceptionHandler {
    public DuplicatedEmailException() {super(ErrorCodes.DUPLICATE_EMAIL);}
}
