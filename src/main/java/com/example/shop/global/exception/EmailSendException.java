package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class EmailSendException extends BusinessExceptionHandler {
    public EmailSendException() {
        super(ErrorCodes.EMAIL_SEND_ERROR);
    }
}
