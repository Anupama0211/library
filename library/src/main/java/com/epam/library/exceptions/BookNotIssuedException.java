package com.epam.library.exceptions;

public class BookNotIssuedException extends RuntimeException {
    public BookNotIssuedException(String message){
        super(message);
    }
}
