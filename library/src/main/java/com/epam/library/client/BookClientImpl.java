package com.epam.library.client;

import com.epam.library.dtos.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookClientImpl implements BookClient{
    @Override
    public List<BookDto> getAllBooks() {
        return null;
    }

    @Override
    public BookDto getABook(int bookId) {
        return null;
    }

    @Override
    public BookDto addABook(BookDto bookDto) {
        return null;
    }

    @Override
    public void removeABook(int bookId) {

    }

    @Override
    public BookDto updateABook(BookDto bookDto, int bookId) {
        return null;
    }
}
