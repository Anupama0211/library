package com.epam.library.restcontrollers;

import com.epam.library.client.BookClient;
import com.epam.library.client.UserClient;
import com.epam.library.dtos.LibraryDto;
import com.epam.library.dtos.UserDto;
import com.epam.library.services.LibraryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(LibraryUserController.class)
class LibraryUserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserClient userClient;
    @MockBean
    BookClient bookClient;
    @MockBean
    LibraryService libraryService;

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllUsers() throws Exception {

        List<UserDto> response = List.of(new UserDto());
        when(userClient.getAllUsers()).thenReturn(response);
        mockMvc.perform(get("/library/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
        ;
        when(userClient.getAllUsers()).thenThrow(FeignException.class);
        mockMvc.perform(get("/library/users"))
                .andExpect(status().isNotFound());

    }

    @Test
    void getAUser() throws Exception {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setBookId(1);
        libraryDto.setUserName("Anupama");

        when(userClient.getAUser("Anupama")).thenReturn(new UserDto());
        when(libraryService.getInfoByUserName("Anupama")).thenReturn(List.of(libraryDto));
        mockMvc.perform(get("/library/users/Anupama"))
                .andExpect(status().isOk());

        verify(libraryService).getInfoByUserName("Anupama");
        verify(bookClient).getABook(1);
        verify(userClient).getAUser("Anupama");

        when(userClient.getAUser("Anupama")).thenThrow(FeignException.class);
        mockMvc.perform(get("/library/users/Anupama"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addAUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("Anu02");
        userDto.setName("Anupama");
        userDto.setEmail("jhaanupama0211@gmail.com");

        when(userClient.addAUser(userDto)).thenReturn(userDto);
        mockMvc.perform(post("/library/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

        userDto.setName("");
        mockMvc.perform(post("/library/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAUser() throws Exception {
        mockMvc.perform(delete("/library/users/Anupama"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateAUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("Anu02");
        userDto.setName("Anupama");
        userDto.setEmail("jhaanupama0211@gmail.com");
        when(userClient.updateAUser(userDto, "Anupama")).thenReturn(userDto);
        mockMvc.perform(put("/library/users/Anupama")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());
    }
}