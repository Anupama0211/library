package com.epam.library.restcontrollers;

import com.epam.library.client.BookClient;
import com.epam.library.client.UserClient;
import com.epam.library.dtos.BookDto;
import com.epam.library.dtos.LibraryDto;
import com.epam.library.dtos.UserDto;
import com.epam.library.services.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("library")
public class LibraryUserController {

    @Autowired
    LibraryService libraryService;

    @Autowired
    BookClient bookClient;

    @Autowired
    UserClient userClient;


    @Operation(description = "It gets all the users")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("users")
    ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userClient.getAllUsers(), HttpStatus.OK);
    }

    @Operation(description = "It gets a user based on the username")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("users/{username}")
    ResponseEntity<UserDto> getAUser(@PathVariable String username) {
        UserDto userDto = userClient.getAUser(username);
        List<LibraryDto> libraryDtos = libraryService.getInfoByUserName(username);
        List<BookDto> bookDtos = new ArrayList<>();
        libraryDtos.forEach(libraryDto -> bookDtos.add(bookClient.getABook(libraryDto.getBookId())));
        userDto.setBooks(bookDtos);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Operation(description = "It creates a user")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("users")
    ResponseEntity<UserDto> addAUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userClient.addAUser(userDto), HttpStatus.CREATED);
    }

    @Operation(description = "It deletes a user")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "204", description = "No Content")
    @DeleteMapping("users/{username}")
    ResponseEntity<HttpStatus> deleteAUser(@PathVariable String username) {
        libraryService.deleteByUserName(username);
        userClient.removeAUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(description = "It updates a user")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping("users/{username}")
    ResponseEntity<UserDto> updateAUser(@PathVariable String username, @RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userClient.updateAUser(userDto, username), HttpStatus.OK);
    }
}
