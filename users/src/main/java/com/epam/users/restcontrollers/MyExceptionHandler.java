package com.epam.users.restcontrollers;


import com.epam.users.dtos.ExceptionResponse;
import com.epam.users.exceptions.UserAlreadyPresentException;
import com.epam.users.exceptions.UserNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class MyExceptionHandler {
    ExceptionResponse getExceptionResponse(String error, HttpStatus badRequest, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setError(error);
        exceptionResponse.setStatus(badRequest.name());
        exceptionResponse.setTimestamp(new Date().toString());
        exceptionResponse.setPath(request.getDescription(false));
        return exceptionResponse;
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( UserAlreadyPresentException.class)
    ResponseEntity<ExceptionResponse> handleUserAlreadyPresentException( UserAlreadyPresentException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    ResponseEntity<ExceptionResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse("No such UserName exists", HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ExceptionResponse exceptionResponse = getExceptionResponse(errors.toString(), HttpStatus.BAD_REQUEST, webRequest);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    ResponseEntity<ExceptionResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse("Email Already present", HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
