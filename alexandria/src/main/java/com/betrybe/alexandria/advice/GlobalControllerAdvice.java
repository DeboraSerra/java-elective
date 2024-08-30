package com.betrybe.alexandria.advice;

import com.betrybe.alexandria.exception.InvalidBody;
import com.betrybe.alexandria.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler
  public ResponseEntity<String> handleNotFound (NotFound exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler
  public ResponseEntity<String> handleWrongBody (InvalidBody exception) {
    return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
  }
}
