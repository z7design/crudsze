package com.tr.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusnessException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  public BusnessException(String message) {
    super(message);
  }
}
