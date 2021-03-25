package com.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleRequestException(BadRequestException e){
        ApiException apiException=new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {InternalException.class})
    public ResponseEntity<Object> handleInternalException(InternalException internalException){
        ApiException apiException=new ApiException(internalException.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,ZonedDateTime.now(ZoneId.of("Z")));
        return  new ResponseEntity<>(apiException,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
