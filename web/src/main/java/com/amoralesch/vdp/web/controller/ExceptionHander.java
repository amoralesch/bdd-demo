package com.amoralesch.vdp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHander {
  @ExceptionHandler
  public ResponseEntity<ApiError> handleException(Exception ex,
    HttpServletRequest req)
  {
    ApiError w = new ApiError(ex, req);

    return ResponseEntity.status(w.getStatus()).body(w);
  }
}
