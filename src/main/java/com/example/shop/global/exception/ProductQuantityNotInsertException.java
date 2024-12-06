package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductQuantityNotInsertException extends BusinessExceptionHandler {


    public ProductQuantityNotInsertException() {
        super(ErrorCodes.NOT_INSERT_PRODUCT_QUANTITY);
    }
}
