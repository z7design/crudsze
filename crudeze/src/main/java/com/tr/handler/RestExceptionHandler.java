package com.tr.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tr.common.DateConveter;
import com.tr.domain.exception.ErrorResponse;
import com.tr.domain.exception.ResourceBadRequestException;
import com.tr.domain.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {

        String dateTime = DateConveter.convertDateToDateETime(new Date());

        ErrorResponse erro = new ErrorResponse(
            dateTime,
            HttpStatus.NOT_FOUND.value(), 
            "Not Found", 
            ex.getMessage());

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResponse> handlerResourceBadRequestException(ResourceBadRequestException ex) {

        String dateTime = DateConveter.convertDateToDateETime(new Date());

        ErrorResponse erro = new ErrorResponse(
            dateTime,
            HttpStatus.BAD_REQUEST.value(), 
            "Bad Request", 
            ex.getMessage());

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerRequestException(Exception ex) {

        String dataHora = DateConveter.convertDateToDateETime(new Date());

        ErrorResponse erro = new ErrorResponse(
            dataHora, 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Internal Server Error", 
            ex.getMessage());

        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
