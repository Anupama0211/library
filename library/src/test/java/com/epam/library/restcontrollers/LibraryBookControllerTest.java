package com.epam.library.restcontrollers;


import com.epam.library.dtos.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibraryBookController.class)
class LibraryBookControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RestTemplate restTemplate;

    @Value("${bookservice.url}")
    String bookServiceUrl;

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllBooks() throws Exception {
        ResponseEntity<List<BookDto>> response = new ResponseEntity<List<BookDto>>(List.of(new BookDto()), HttpStatus.OK);
        when(restTemplate.exchange(bookServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
        })).thenReturn(response);
        mockMvc.perform(get("/library/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response.getBody())));

        when(restTemplate.exchange(bookServiceUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<BookDto>>() {
        })).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not Found", new byte[]{}, Charset.defaultCharset()));
        mockMvc.perform(get("/library/books"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getABook() throws Exception {
        ResponseEntity<BookDto> response = new ResponseEntity<BookDto>(new BookDto(), HttpStatus.OK);
        when(restTemplate.exchange(bookServiceUrl.concat("/1"), HttpMethod.GET, null, BookDto.class)).thenReturn(response);
        mockMvc.perform(get("/library/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response.getBody())));
    }

    @Test
    void addABook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("ABCDE");
        bookDto.setName("12345");
        bookDto.setPublisher("Publisher");
        ResponseEntity<BookDto> response = new ResponseEntity<BookDto>(bookDto, HttpStatus.CREATED);
        when(restTemplate.postForEntity(anyString(), any(BookDto.class), eq(BookDto.class))).thenReturn(response);

        mockMvc.perform(post("/library/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response.getBody())));


        bookDto.setPublisher("");
        mockMvc.perform(post("/library/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void removeABook() throws Exception {
        mockMvc.perform(delete("/library/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateABook() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("ABCDE");
        bookDto.setName("12345");
        bookDto.setPublisher("Publisher");
        mockMvc.perform(put("/library/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());
    }
}