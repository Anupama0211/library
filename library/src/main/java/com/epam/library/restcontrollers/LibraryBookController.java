package com.epam.library.restcontrollers;

import com.epam.library.dtos.BookDto;
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

    @GetMapping("books")
    ResponseEntity<List<BookDto>> getAllBooks() {
        return restTemplate.exchange(bookServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
        });

    }

    @GetMapping("books/{bookId}")
    ResponseEntity<BookDto> getABook(@PathVariable int bookId) {
        String url = bookServiceUrl.concat("/" + bookId);
        return restTemplate.exchange(url, HttpMethod.GET, null, BookDto.class);
    }

    @PostMapping("books")
    ResponseEntity<BookDto> addABook(@RequestBody @Valid BookDto bookDto) {
        ResponseEntity<BookDto> responseEntity = restTemplate.postForEntity(bookServiceUrl, bookDto, BookDto.class);
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.CREATED);
    }

    @DeleteMapping("books/{bookId}")
    ResponseEntity<HttpStatus> removeABook(@PathVariable int bookId) {
        restTemplate.delete(bookServiceUrl.concat("/" + bookId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("books/{bookId}")
    ResponseEntity<HttpStatus> updateABook(@PathVariable int bookId, @RequestBody @Valid BookDto bookDto) {
        restTemplate.put(bookServiceUrl.concat("/" + bookId), bookDto, BookDto.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
