package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class EmailCreationException extends BusinessExceptionHandler {
    public EmailCreationException() {
        super(ErrorCodes.EMAIL_FORM_CREATION_ERROR);
    }
}
