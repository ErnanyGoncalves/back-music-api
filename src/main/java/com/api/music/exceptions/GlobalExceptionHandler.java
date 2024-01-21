package com.api.music.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {


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

    return handleExceptionInternal(ex, response, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    logger.error(ex.getMessage(), ex);
    if (!(body instanceof ErrorResponse)) {
      body = obtainResponseInternalError(ex,request);
    }
    return super.handleExceptionInternal(ex, body, headers, statusCode, request);
  }


  private ErrorResponse500 obtainResponseInternalError(Exception ex, WebRequest request) {

    final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    final String message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();

    return new ErrorResponse500(status,ex.getMessage(),message,request.getDescription(false));
  }


  @AllArgsConstructor
  @Getter
  private static class ErrorResponse {
    private final Integer status;
    private final String message;
    private final List<ErrorDetail> errors;

  }

  @AllArgsConstructor
  @Getter
  private static class ErrorResponse500 {
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;
  }

  @AllArgsConstructor
  @Getter
  private static class ErrorDetail {
    private final String message;
    private final String objectName;
    private final String field;


  }
}

