package com.ecommerce.cart.advise;

import com.ecommerce.cart.dto.ErrorResponse;
import com.ecommerce.cart.exceptions.ResourceNotFound;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFound e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(e.getErrorCode()));
    }
}
