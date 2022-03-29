package com.epam.books.restcontrollers;

import com.epam.books.dto.BookDto;
import com.epam.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;
    @GetMapping
    ResponseEntity<List<BookDto>> viewBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping("{bookId}")
    ResponseEntity<BookDto> viewBook(@PathVariable int bookId){
        return new ResponseEntity<>(bookService.getBookByID(bookId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<BookDto> addABook(@RequestBody BookDto bookDto){

    }

}
