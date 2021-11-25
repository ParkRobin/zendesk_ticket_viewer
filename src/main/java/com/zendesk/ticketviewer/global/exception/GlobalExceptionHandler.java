package com.zendesk.ticketviewer.global.exception;

import com.zendesk.ticketviewer.web.response.ResponseData;
import com.zendesk.ticketviewer.web.response.ResponseUtil;
import com.zendesk.ticketviewer.web.response.VoidData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = TicketViewException.class)
    public ResponseData<VoidData> cacheWarnException(TicketViewException e, HttpServletRequest request) {
        log.warn("Program custom warning. warn:{}", e.getMessage());
        return ResponseUtil.error(e, request);
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<VoidData> unknownException(Exception e, HttpServletRequest request) {
        log.error("Unknown exception on the server. Class:{}.   exception:{}", e.getClass().getName(), e.getMessage(), e);
        return ResponseUtil.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseData<VoidData> cacheIllegalArgumentException(IllegalArgumentException e,
                                                                HttpServletRequest request) {
        log.warn("The request parameter is invalid.  exception:{}", e.getMessage(), e);
        return ResponseUtil.error(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), request);
    }

    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseData<VoidData> cacheHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn("Illegal request type. <GET|POST|PUT|DELETE>.    exception:{}", e.getMessage(), e);
        return ResponseUtil.error(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseData<VoidData> cacheMissingServletRequestParameterException(
            MissingServletRequestParameterException e, HttpServletRequest request) {
        log.warn("Request parameters missing.    exception:{}", e.getMessage(), e);
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseData<VoidData> cacheHttpMessageNotReadableException(
            HttpMessageNotReadableException e, HttpServletRequest request) {
        log.warn("Illegal request body. exception：{}", e.getMessage());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), e.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseData<VoidData> defaultErrorHandler(NoHandlerFoundException e,
                                                      HttpServletRequest request) {
        log.warn("Request URL does not exist.  exception：{}", e.getMessage());
        return ResponseUtil.error(HttpStatus.NOT_FOUND.value(), e.getMessage(), request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseData<VoidData> catchConstraintViolationException(ConstraintViolationException e,
                                                                    HttpServletRequest request) {
        log.warn("Basic type request parameter verification failed.     exception：{}", e.getMessage());
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation c : e.getConstraintViolations()) {
            sb.append(c.getMessage()).append(". ");
        }
        String msg = sb.toString();
        sb.delete(0, sb.length());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), msg, request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<VoidData> cacheMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("Request body parameter verification failed.    exception：{}", e.getMessage());
        StringBuilder sb = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            sb.append(error.getField()).append(":").append(error.getDefaultMessage())
                    .append(". ");
        }
        String msg = sb.toString();
        sb.delete(0, sb.length());
        return ResponseUtil.error(HttpStatus.BAD_REQUEST.value(), msg, request);
    }
}
