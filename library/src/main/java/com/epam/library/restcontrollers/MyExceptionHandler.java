package com.epam.library.restcontrollers;


import com.epam.library.dtos.ExceptionResponse;
import com.epam.library.exceptions.BookNotIssuedException;
import com.epam.library.exceptions.MaxBooksIssuedException;
import feign.FeignException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
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

    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException exception, WebRequest request) {
        String body=exception.getResponseBodyAsString();
        HttpStatus status=exception.getStatusCode();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        return new ResponseEntity<>(body,httpHeaders,status);
    }

    @ExceptionHandler(BookNotIssuedException.class)
    ResponseEntity<ExceptionResponse> handleBookNotIssuedException(BookNotIssuedException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxBooksIssuedException.class)
    ResponseEntity<ExceptionResponse> handleMaxBooksIssuedException(MaxBooksIssuedException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, request);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FeignException.class)
    protected ResponseEntity<String> handleFeignException(FeignException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(ex.status());
        String body = ex.contentUTF8();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        return new ResponseEntity<>(body,httpHeaders,status);
    }


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    ResponseEntity<ExceptionResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception, WebRequest request) {
        ExceptionResponse exceptionResponse = getExceptionResponse("The book is already issued!", HttpStatus.NOT_FOUND, request);
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
