package com.online.ecommerce.exception;

public class ProductDoesNotExistException extends IllegalArgumentException {
    public ProductDoesNotExistException(String msg) {
        super(msg);
    }
}
