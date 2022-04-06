package com.epam.library.exceptions;

import feign.FeignException;

public class SampleFeingException extends FeignException {
    public SampleFeingException(int status, String message) {
        super(status, message);
    }
}
