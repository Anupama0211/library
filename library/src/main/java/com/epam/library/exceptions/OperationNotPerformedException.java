package com.epam.library.exceptions;

public class OperationNotPerformedException extends RuntimeException {
    public OperationNotPerformedException(String message) {
        super(message);
    }
}
