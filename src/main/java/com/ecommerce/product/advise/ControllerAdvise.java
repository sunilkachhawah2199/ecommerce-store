package com.ecommerce.product.advise;

import com.ecommerce.product.dto.ErrorDto;
import com.ecommerce.product.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // this annotation is used to handle exceptions globally
public class ControllerAdvise {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto>  handleProductNotFoundException(ProductNotFoundException e){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(e.getErrorCode()));
    }
}
