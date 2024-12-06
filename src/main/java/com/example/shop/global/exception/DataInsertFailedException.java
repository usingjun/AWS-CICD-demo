package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class DataInsertFailedException extends BusinessExceptionHandler {
    public DataInsertFailedException() {
        super(ErrorCodes.NOT_SAVE_CREATE);
    }
}
