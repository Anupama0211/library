package com.epam.library.restcontrollers;


import com.epam.library.client.BookClient;
import com.epam.library.client.UserClient;
import com.epam.library.dtos.LibraryDto;
import com.epam.library.services.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;
    @Autowired
    BookClient bookClient;

    @Autowired
    UserClient userClient;

    @Operation(description = "It issues a book to a user")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @PostMapping("users/{username}/books/{bookId}")
    ResponseEntity<LibraryDto> issueBookToAUser(@PathVariable String username, @PathVariable int bookId) {
        userClient.getAUser(username);
        bookClient.getABook(bookId);
        return new ResponseEntity<>(libraryService.issueABookToAUser(username, bookId), HttpStatus.OK);
    }

    @Operation(description = "It releases a book for a user")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "204", description = "No Content")
    @DeleteMapping("users/{username}/books/{bookId}")
    ResponseEntity<HttpStatus> releaseBookForAUser(@PathVariable String username, @PathVariable int bookId) {
        userClient.getAUser(username);
        bookClient.getABook(bookId);
        libraryService.releaseBookForAUser(username,bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
