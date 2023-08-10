package com.tr.domain.exception;

import java.time.Instant;
import java.util.zip.DataFormatException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandle {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StanderdError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
    HttpStatus status = HttpStatus.NOT_FOUND;
    StanderdError err = new StanderdError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Resource no found");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(DataFormatException.class)
  public ResponseEntity<StanderdError> database(DatabaseException e, HttpServletRequest request){
    HttpStatus status = HttpStatus.NOT_FOUND;
    StanderdError err = new StanderdError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Database exception");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(err);
  }
}
