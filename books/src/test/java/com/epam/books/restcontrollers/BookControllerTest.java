package com.epam.books.restcontrollers;

import com.epam.books.dtos.BookDto;
import com.epam.books.exceptions.NoBookFoundException;
import com.epam.books.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    BookService bookService;

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }


    @Test
    void viewBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(List.of(new BookDto()));
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        when(bookService.getAllBooks()).thenThrow(NoBookFoundException.class);
        mockMvc.perform(get("/books"))
                .andExpect(status().isNotFound());

    }

    @Test
    void viewBook() throws Exception {
        when(bookService.getBookByID(1)).thenReturn(new BookDto());
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk());

        when(bookService.getBookByID(1)).thenThrow(NoBookFoundException.class);
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void addABook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("ABCDE");
        bookDto.setName("12345");
        bookDto.setPublisher("Publisher");
        when(bookService.addBook(bookDto)).thenReturn(bookDto);
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isCreated());


        bookDto.setPublisher("");
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteABook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());

        doThrow(EmptyResultDataAccessException.class).when(bookService).removeBook(1);
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void updateABook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("ABCDE");
        bookDto.setName("12345");
        bookDto.setPublisher("Publisher");
        when(bookService.updateBook(bookDto,1)).thenReturn(bookDto);
        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());
    }
}