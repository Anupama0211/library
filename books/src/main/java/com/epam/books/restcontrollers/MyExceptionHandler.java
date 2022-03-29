package com.epam.books.restcontrollers;

import com.epam.books.dto.ExceptionResponse;
import com.epam.books.exceptions.NoBookFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

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


}
