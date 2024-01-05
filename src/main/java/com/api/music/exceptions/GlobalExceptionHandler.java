package com.api.music.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
    BindingResult result = ex.getBindingResult();

    List<ErrorDetail> errors = result.getFieldErrors().stream()
        .map(fieldError -> new ErrorDetail(
            fieldError.getDefaultMessage(),
            fieldError.getObjectName(),
            fieldError.getField()
        ))
        .collect(Collectors.toList());

    ErrorResponse response = new ErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        errors
    );

    return ResponseEntity.badRequest().body(response);
  }

  @AllArgsConstructor
  @Getter
  private static class ErrorResponse {
    private final int status;
    private final String message;
    private final List<ErrorDetail> errors;

  }

  @AllArgsConstructor
  @Getter
  private static class ErrorDetail {
    private final String message;
    private final String objectName;
    private final String field;


  }
}