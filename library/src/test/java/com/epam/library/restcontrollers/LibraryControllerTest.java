package com.epam.library.restcontrollers;

import com.epam.library.client.BookClient;
import com.epam.library.client.UserClient;
import com.epam.library.exceptions.BookNotIssuedException;
import com.epam.library.exceptions.MaxBooksIssuedException;
import com.epam.library.services.LibraryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserClient userClient;
    @MockBean
    BookClient bookClient;
    @MockBean
    LibraryService libraryService;

    @Test
    void issueBookToAUser() throws Exception {

        mockMvc.perform(post("/library/users/Anupama/books/1")).andExpect(status().isOk());

        when(libraryService.issueABookToAUser(anyString(), anyInt())).thenThrow(MaxBooksIssuedException.class);
        mockMvc.perform(post("/library/users/Anupama/books/1")).andExpect(status().isBadRequest());

//        when(libraryService.issueABookToAUser("Anupama", 1)).thenThrow(SQLIntegrityConstraintViolationException.class);
//        mockMvc.perform(post("/library/users/Anupama/books/1")).andExpect(status().isNotFound());

    }

    @Test
    void releaseBookForAUser() throws Exception {
        mockMvc.perform(delete("/library/users/Anupama/books/1")).andExpect(status().isNoContent());

        doThrow(BookNotIssuedException.class).when(libraryService).releaseBookForAUser("Anupama", 1);
        mockMvc.perform(delete("/library/users/Anupama/books/1")).andExpect(status().isNotFound());
    }
}