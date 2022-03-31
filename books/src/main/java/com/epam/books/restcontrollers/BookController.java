package com.epam.books.restcontrollers;

import com.epam.books.dtos.BookDto;
import com.epam.books.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    ResponseEntity<List<BookDto>> viewBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("{bookId}")
    ResponseEntity<BookDto> viewBook(@PathVariable int bookId) {
        return new ResponseEntity<>(bookService.getBookByID(bookId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<BookDto> addABook(@RequestBody @Valid BookDto bookDto) {
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{bookId}")
    ResponseEntity<HttpStatus> deleteABook(@PathVariable int bookId) {
        bookService.removeBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{bookId}")
    ResponseEntity<BookDto> updateABook(@PathVariable int bookId, @RequestBody @Valid BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBook(bookDto, bookId), HttpStatus.OK);
    }

}
