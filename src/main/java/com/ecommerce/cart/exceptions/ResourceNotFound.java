package com.ecommerce.cart.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFound extends RuntimeException{
    private int errorCode;
    public ResourceNotFound(String message, int errorCode) {
        // RuntimeException has 1 constructor that takes a string as an argument
        super(message);
        this.errorCode = errorCode;
    }
}
