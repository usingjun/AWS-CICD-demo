package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductPriceNotInsertException extends BusinessExceptionHandler {


    public ProductPriceNotInsertException() {
        super(ErrorCodes. NOT_INSERT_PRODUCT_PRICE);
    }
}
