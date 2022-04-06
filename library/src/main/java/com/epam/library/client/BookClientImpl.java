package com.epam.library.client;

import com.epam.library.dtos.BookDto;
import com.epam.library.exceptions.OperationNotPerformedException;
import feign.FeignException;
import lombok.Setter;


import java.util.List;


public class BookClientImpl implements BookClient {
    private  @Setter Throwable cause;
    private static final String MESSAGE = "Operation Cannot be performed";

    public BookClientImpl(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<BookDto> getAllBooks() {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        return List.of(new BookDto());
    }

    @Override
    public BookDto getABook(int bookId) {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        return new BookDto();
    }

    @Override
    public BookDto addABook(BookDto bookDto) {
        if (cause instanceof FeignException feignException && feignException.status() == 400) {
            throw feignException;
        }
        throw new OperationNotPerformedException(MESSAGE);
    }

    @Override
    public void removeABook(int bookId) {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        throw new OperationNotPerformedException(MESSAGE);
    }

    @Override
    public BookDto updateABook(BookDto bookDto, int bookId) {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        throw new OperationNotPerformedException(MESSAGE);
    }
}
