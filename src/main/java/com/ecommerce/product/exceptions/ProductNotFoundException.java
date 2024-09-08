package com.ecommerce.product.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{
    private int errorCode;
     public  ProductNotFoundException(String message, int errorCode) {
         super(message);
         this.errorCode = errorCode;
     }
}
