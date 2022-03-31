package com.epam.library.exceptions;

public class MaxBooksIssuedException extends RuntimeException {
    public MaxBooksIssuedException(String message) {
        super(message);
    }

}
