package com.mateuszgeborski.gradesbackend.api.v1.service.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFoundException(ResourceNotFoundException exc){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(exc.getMessage())
                        .timeStamp(System.currentTimeMillis())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
