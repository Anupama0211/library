package com.epam.library.client;

import com.epam.library.dtos.BookDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@LoadBalancerClient(name="book-service")
@FeignClient(name = "book-service",fallback = BookClientImpl.class)
public interface BookClient {

    @GetMapping("books")
    public List<BookDto> getAllBooks();

    @GetMapping("books/{bookId}")
    public BookDto getABook(@PathVariable int bookId);

    @PostMapping("books")
    public BookDto addABook(@RequestBody BookDto bookDto);

    @DeleteMapping("books/{bookId}")
    public void removeABook(@PathVariable int bookId);

    @PutMapping("books/{bookId}")
    public BookDto updateABook(@RequestBody BookDto bookDto, @PathVariable int bookId);

}
