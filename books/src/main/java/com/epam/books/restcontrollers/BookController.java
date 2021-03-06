package com.epam.books.restcontrollers;

import com.epam.books.dtos.BookDto;
import com.epam.books.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(description = "It gets all the books")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    ResponseEntity<List<BookDto>> viewBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @Operation(description = "It gets a book based on the bookId")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("{bookId}")
    ResponseEntity<BookDto> viewBook(@PathVariable int bookId) {
        return new ResponseEntity<>(bookService.getBookByID(bookId), HttpStatus.OK);
    }

    @Operation(description = "It adds a book")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping
    ResponseEntity<BookDto> addABook(@RequestBody @Valid BookDto bookDto) {
        return new ResponseEntity<>(bookService.addBook(bookDto), HttpStatus.CREATED);
    }

    @Operation(description = "It deletes a book")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "204", description = "No Content")
    @DeleteMapping("{bookId}")
    ResponseEntity<HttpStatus> deleteABook(@PathVariable int bookId) {
        bookService.removeBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(description = "It updates a book")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping("{bookId}")
    ResponseEntity<BookDto> updateABook(@PathVariable int bookId, @RequestBody @Valid BookDto bookDto) {
        return new ResponseEntity<>(bookService.updateBook(bookDto, bookId), HttpStatus.OK);
    }

}
