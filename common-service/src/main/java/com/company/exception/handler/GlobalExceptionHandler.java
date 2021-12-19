package com.company.exception.handler;

import com.company.exception.AccessDeniedException;
import com.company.exception.BadRequestException;
import com.company.exception.NotFoundException;
import com.company.exception.model.ExceptionResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.ServiceUnavailableException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public final ExceptionResponse handle(NotFoundException ex, WebRequest request){
        log.trace("Resource not found {}", ex.getMessage(), ex);
        return handleException(request,HttpStatus.NOT_FOUND,ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public final ExceptionResponse handle(AuthenticationException ex,
                                                      WebRequest request) {
        log.trace("Authentication failed {}", ex.getMessage());
        return handleException(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ExceptionResponse handle(AccessDeniedException ex,
                                                            WebRequest request) {
        log.trace("Access denied {}", ex.getMessage());
        return handleException(request, HttpStatus.FORBIDDEN, ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ExceptionResponse handle(ConstraintViolationException ex,
                                                            WebRequest request) {
        log.trace("Constraints violated {}", ex.getMessage());
        return handleException(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ExceptionResponse handle(MethodArgumentTypeMismatchException ex,
                                                            WebRequest request) {
        log.trace("Method arguments are not valid {}", ex.getMessage());
        return handleException(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MismatchedInputException.class)
    public final ExceptionResponse handle(MismatchedInputException ex,
                                                            WebRequest request) {
        log.trace("Mismatched input {}", ex.getMessage());
        return handleException(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public final ExceptionResponse handle(ServiceUnavailableException ex, WebRequest request) {
        log.trace("Service unavailable {}", ex.getMessage());
        return handleException(request, HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ExceptionResponse handle(DataIntegrityViolationException ex,
                                                            WebRequest request) {
        log.trace("SQL Integrity Constraint violated {}", ex.getMessage());
        return handleException(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public final ExceptionResponse handle(BadRequestException ex, WebRequest request) {
        log.trace("Bad request {}", ex.getMessage());
        return handleException(request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    public ExceptionResponse handleException(WebRequest request, HttpStatus status, String message){
        return ExceptionResponse
                .builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .error(status.getReasonPhrase())
                .message(message)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
    }
}
