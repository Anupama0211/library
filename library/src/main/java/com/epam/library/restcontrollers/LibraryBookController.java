package com.epam.library.restcontrollers;

import com.epam.library.dtos.BookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("library")
public class LibraryBookController {

    @Value("${bookservice.url}")
    String bookServiceUrl;

    @Autowired
    RestTemplate restTemplate;

    @Operation(description = "It gets all the books")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("books")
    ResponseEntity<List<BookDto>> getAllBooks() {
        return restTemplate.exchange(bookServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
        });

    }

    @Operation(description = "It gets a book based on the bookId")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("books/{bookId}")
    ResponseEntity<BookDto> getABook(@PathVariable int bookId) {
        String url = bookServiceUrl.concat("/" + bookId);
        return restTemplate.exchange(url, HttpMethod.GET, null, BookDto.class);
    }

    @Operation(description = "It adds a book")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping("books")
    ResponseEntity<BookDto> addABook(@RequestBody @Valid BookDto bookDto) {
        ResponseEntity<BookDto> responseEntity = restTemplate.postForEntity(bookServiceUrl, bookDto, BookDto.class);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.CREATED);
    }

    @Operation(description = "It deletes a book")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "204", description = "No Content")
    @DeleteMapping("books/{bookId}")
    ResponseEntity<HttpStatus> removeABook(@PathVariable int bookId) {
        restTemplate.delete(bookServiceUrl.concat("/" + bookId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(description = "It updates a book")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping("books/{bookId}")
    ResponseEntity<HttpStatus> updateABook(@PathVariable int bookId, @RequestBody @Valid BookDto bookDto) {
        restTemplate.put(bookServiceUrl.concat("/" + bookId), bookDto, BookDto.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
