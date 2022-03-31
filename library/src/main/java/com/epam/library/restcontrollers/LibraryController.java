package com.epam.library.restcontrollers;


import com.epam.library.client.BookClient;
import com.epam.library.client.UserClient;
import com.epam.library.dtos.LibraryDto;
import com.epam.library.services.LibraryService;
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

    @PostMapping("users/{username}/books/{bookId}")
    ResponseEntity<LibraryDto> issueBookToAUser(@PathVariable String username, @PathVariable int bookId) {
        userClient.getAUser(username);
        bookClient.getABook(bookId);
        return new ResponseEntity<>(libraryService.issueABookToAUser(username, bookId), HttpStatus.OK);
    }

    @DeleteMapping("users/{username}/books/{bookId}")
    ResponseEntity<HttpStatus> releaseBookForAUser(@PathVariable String username, @PathVariable int bookId) {
        userClient.getAUser(username);
        bookClient.getABook(bookId);
        libraryService.releaseBookForAUser(username,bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
