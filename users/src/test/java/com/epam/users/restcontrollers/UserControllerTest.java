package com.epam.users.restcontrollers;

import com.epam.users.dtos.UserDto;
import com.epam.users.exceptions.UserAlreadyPresentException;
import com.epam.users.exceptions.UserNotFoundException;
import com.epam.users.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
    }


    @Test
    void viewUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(new UserDto()));
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        when(userService.getAllUsers()).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/books"))
                .andExpect(status().isNotFound());
    }

    @Test
    void viewUser() throws Exception {
        when(userService.getUserById("Anupama")).thenReturn(new UserDto());
        mockMvc.perform(get("/users/Anupama"))
                .andExpect(status().isOk());

        when(userService.getUserById("Anupama")).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/users/Anupama"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addAUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("Anu02");
        userDto.setName("Anupama");
        userDto.setEmail("jhaanupama0211@gmail.com");

        when(userService.addUser(userDto)).thenReturn(userDto);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());


        userDto.setName("");
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addUserWhenAlreadyPresent() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("Anu02");
        userDto.setName("Anupama");
        userDto.setEmail("jhaanupama0211@gmail.com");
        when(userService.addUser(any(UserDto.class))).thenThrow(UserAlreadyPresentException.class);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest());
        verify(userService).addUser(any(UserDto.class));
    }

//    @Test
//    void addUserWhenEmailAlreadyPresent() throws Exception {
//        UserDto userDto = new UserDto();
//        userDto.setUserName("Anu02");
//        userDto.setName("Anupama");
//        userDto.setEmail("jhaanupama0211@gmail.com");
//        when(userService.addUser(any(UserDto.class))).thenThrow(new SQLIntegrityConstraintViolationException());
//        mockMvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userDto)))
//                .andExpect(status().isBadRequest());
//        System.out.println(userService.addUser(userDto));
//        verify(userService).addUser(any(UserDto.class));
//    }

    @Test
    void deleteAUser() throws Exception {
        mockMvc.perform(delete("/users/Anuapama"))
                .andExpect(status().isNoContent());

        doThrow(EmptyResultDataAccessException.class).when(userService).removeUser("Anupama");
        mockMvc.perform(delete("/users/Anupama"))
                .andExpect(status().isNotFound());

    }

    @Test
    void updateAUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserName("Anu02");
        userDto.setName("Anupama");
        userDto.setEmail("jhaanupama0211@gmail.com");
        when(userService.updateUser(userDto, "Anupama")).thenReturn(userDto);
        mockMvc.perform(put("/users/Anupama")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

    }
}