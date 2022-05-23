package com.example.demo.handlers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {
    List<String> errors = new ArrayList<String>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }

    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    Timestamp timestamp = Timestamp.from(Instant.now());
    String path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ApiError apiError = new ApiError(timestamp.getTime(), status.value(), status.getReasonPhrase(), errors, path);
    return handleExceptionInternal(ex, apiError, headers, status, request);
  }
}

class ApiError {
  private long timestamp;
  private int status;
  private String error;
  private List<String> message;
  private String path;

  public ApiError() {}

  public ApiError(long timestamp, int status, String error, List<String> message, String path) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public List<String> getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }
}