package com.epam.books.services;

import com.epam.books.dtos.BookDto;
import com.epam.books.entities.Book;
import com.epam.books.exceptions.NoBookFoundException;
import com.epam.books.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    BookService bookService;
    BookDto bookDto;
    Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setAuthor("Author");
        book.setName("Name1234");
        book.setPublisher("Publisher");

        bookDto = new BookDto();
        bookDto.setAuthor("Author");
        bookDto.setName("Name1234");
        bookDto.setPublisher("Publisher");
    }

    @Test
    void getAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(modelMapper.map(List.of(book), new TypeToken<List<BookDto>>() {
        }.getType())).thenReturn(List.of(bookDto));
        assertThat(bookService.getAllBooks()).isEqualTo(List.of(bookDto));
        when(bookRepository.findAll()).thenReturn(List.of());
        assertThrows(NoBookFoundException.class, () -> bookService.getAllBooks());

    }

    @Test
    void addBook() {
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
        assertThat(bookService.addBook(bookDto)).isEqualTo(bookDto);
        verify(bookRepository).save(book);

    }

    @Test
    void removeBook() {
        bookService.removeBook(1);
        verify(bookRepository).deleteById(1);
    }

    @Test
    void getBookByID() {
        when(bookRepository.findById(1)).thenReturn(Optional.ofNullable(book));
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
        assertThat(bookService.getBookByID(1)).isEqualTo(bookDto);
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoBookFoundException.class, () -> bookService.getBookByID(1));
    }

    @Test
    void updateBook() {
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(bookDto, Book.class)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(bookDto);
        when(bookRepository.findById(1)).thenReturn(Optional.ofNullable(book));
        assertThat(bookService.updateBook(bookDto,1)).isEqualTo(bookDto);
        verify(bookRepository).save(book);
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NoBookFoundException.class, () -> bookService.updateBook(bookDto,1));
    }
}