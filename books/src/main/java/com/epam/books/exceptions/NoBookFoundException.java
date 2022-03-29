package com.epam.books.exceptions;

public class NoBookFoundException extends RuntimeException{
    public NoBookFoundException(String message){
        super(message);
    }

}
