package com.ecommerce.advise;

import com.ecommerce.dto.ErrorDto;
import com.ecommerce.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
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
//        errorDto.setCode("some_error_code");
        errorDto.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
