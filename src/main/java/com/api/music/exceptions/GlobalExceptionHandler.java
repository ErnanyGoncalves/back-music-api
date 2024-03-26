package com.api.music.exceptions;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
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

    BadRequestErrorResponse response = new BadRequestErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        errors
    );

    return handleExceptionInternal(ex, response, headers, status, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleInternalServerException(Exception ex,
       WebRequest request) {
    return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleTypeMismatchException(MethodArgumentTypeMismatchException ex,
       WebRequest request) {
    return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    logger.error(ex.getMessage(), ex);
    if (Objects.isNull(body) && !(body instanceof BadRequestErrorResponse)) {
      body = obtainResponse(statusCode,ex,request);
    }
    return super.handleExceptionInternal(ex, body, headers, statusCode, request);
  }


  private GenericErrorResponse obtainResponse(HttpStatusCode status, Exception ex, WebRequest request) {

    final HttpStatusCode newStatus = Optional.ofNullable(status).orElse(HttpStatusCode.valueOf(500));
    final String message = HttpStatus.valueOf(newStatus.value()).getReasonPhrase();

    return new GenericErrorResponse(newStatus.value(),ex.getMessage(),message,request.getDescription(false));
  }


  @AllArgsConstructor
  @Getter
  private static class BadRequestErrorResponse {
    private final Integer status;
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
  @AllArgsConstructor
  @Getter
  private static class GenericErrorResponse {
    private final Integer status;
    private final String error;
    private final String message;
    private final String path;
  }
  }


