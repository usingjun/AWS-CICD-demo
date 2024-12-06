package com.example.shop.global.exception;

import com.example.shop.global.response.BusinessExceptionHandler;

public class ProductsSearchByOneLetterException extends BusinessExceptionHandler {
    public ProductsSearchByOneLetterException() {
        super(ErrorCodes.PRODUCTS_SEARCH_BY_ONE_LETTER);
    }
}
