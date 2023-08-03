package com.tr.handle;

import com.tr.common.ConversorDate;
import com.tr.domain.exception.ErrorResponse;
import com.tr.domain.exception.ResourceBadRequestException;
import com.tr.domain.exception.ResourceNotFoundException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
  // Com esse cara eu consigo pegar qualquer requisição do tipo NotFound
  @ExceptionHandler(ResourceNotFoundException.class)
  ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {
    String dateTimes = ConversorDate.convertionTheDateToDateAndTime(new Date());
    ErrorResponse errorResponse =
        new ErrorResponse(dateTimes, HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorResponse> handlerBadRequestException(Exception ex) {
    String dateTimes = ConversorDate.convertionTheDateToDateAndTime(new Date());
    ErrorResponse errorResponse =
        new ErrorResponse(dateTimes, HttpStatus.BAD_REQUEST.value(), "Not Found", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

 @ExceptionHandler(ResourceBadRequestException.class)
  ResponseEntity<ErrorResponse> handlerRequestException(Exception ex) {
    String dateTimes = ConversorDate.convertionTheDateToDateAndTime(new Date());
    ErrorResponse errorResponse =
        new ErrorResponse(dateTimes, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
