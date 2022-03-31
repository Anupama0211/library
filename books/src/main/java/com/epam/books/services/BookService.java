package com.epam.books.services;

import com.epam.books.dtos.BookDto;
import com.epam.books.entities.Book;
import com.epam.books.repository.BookRepository;
import com.epam.books.exceptions.NoBookFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<BookDto> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        if (books.isEmpty()) {
            throw new NoBookFoundException("No Books Present!!!");
        }
        return modelMapper.map(books, new TypeToken<List<BookDto>>() {
        }.getType());
    }

    public BookDto addBook(BookDto bookDto) {
        Book book = bookRepository.save(modelMapper.map(bookDto, Book.class));
        return modelMapper.map(book, BookDto.class);
    }

    public void removeBook(int bookId) {
        bookRepository.deleteById(bookId);
    }

    public BookDto getBookByID(int bookId) {
        return modelMapper
                .map(bookRepository
                                .findById(bookId)
                                .orElseThrow(() -> new NoBookFoundException("Invalid Book ID!!"))
                        , BookDto.class);
    }

    public BookDto updateBook(BookDto bookDto, int bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            bookDto.setId(bookId);
            return addBook(bookDto);
        } else {
            throw new NoBookFoundException("Invalid Book ID!!");
        }
    }
}
