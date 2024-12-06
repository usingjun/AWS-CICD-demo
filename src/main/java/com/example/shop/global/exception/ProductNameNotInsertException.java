package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductNameNotInsertException extends BusinessExceptionHandler {


    public ProductNameNotInsertException() {
        super(ErrorCodes.NOT_INSERT_PRODUCT_NAME);
    }
}
