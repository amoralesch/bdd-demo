package com.amoralesch.vdp.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

public class ApiError {
  private final HttpStatus httpStatus;

  private final String exception;

  private final String message;

  public ApiError(Exception ex, HttpServletRequest req)
  {
    Class<?> clazz = ex.getClass();

    httpStatus = getStatus(clazz);
    exception = clazz.getSimpleName();
    message = ex.getMessage();
  }

  public int getStatus()
  {
    return httpStatus.value();
  }

  public String getError()
  {
    return httpStatus.getReasonPhrase();
  }

  public String getException()
  {
    return exception;
  }

  public String getMessage()
  {
    return message;
  }

  private HttpStatus getStatus(Class<?> clazz)
  {
    ResponseStatus a = clazz.getAnnotation(ResponseStatus.class);

    if (a != null)
      return (HttpStatus)AnnotationUtils.getValue(a);

    return getStaticStatus(clazz);
  }

  private HttpStatus getStaticStatus(Class<?> clazz)
  {
    if (HttpRequestMethodNotSupportedException.class.isAssignableFrom(clazz))
      return HttpStatus.METHOD_NOT_ALLOWED;

    if (HttpMediaTypeNotSupportedException.class.isAssignableFrom(clazz))
      return HttpStatus.UNSUPPORTED_MEDIA_TYPE;

    if (HttpMediaTypeNotAcceptableException.class.isAssignableFrom(clazz))
      return HttpStatus.NOT_ACCEPTABLE;

    if (MissingServletRequestParameterException.class.isAssignableFrom(clazz) ||
      ServletRequestBindingException.class.isAssignableFrom(clazz) ||
      TypeMismatchException.class.isAssignableFrom(clazz) ||
      HttpMessageNotReadableException.class.isAssignableFrom(clazz) ||
      MethodArgumentNotValidException.class.isAssignableFrom(clazz) ||
      MissingServletRequestPartException.class.isAssignableFrom(clazz) ||
      BindException.class.isAssignableFrom(clazz))
      return HttpStatus.BAD_REQUEST;

    if (NoHandlerFoundException.class.isAssignableFrom(clazz))
      return HttpStatus.NOT_FOUND;

    if (AsyncRequestTimeoutException.class.isAssignableFrom(clazz))
      return HttpStatus.SERVICE_UNAVAILABLE;

    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
