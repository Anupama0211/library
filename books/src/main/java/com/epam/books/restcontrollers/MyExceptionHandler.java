package com.epam.books.restcontrollers;

import com.epam.books.dtos.ExceptionResponse;
import com.epam.books.exceptions.NoBookFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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

    @ExceptionHandler(NoBookFoundException.class)
    ResponseEntity<ExceptionResponse> handleInvalidIDException(NoBookFoundException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    ResponseEntity<ExceptionResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse("No such book ID exists", HttpStatus.NOT_FOUND, request);
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


}
