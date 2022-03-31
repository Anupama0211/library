package com.epam.library.services;

import com.epam.library.dtos.LibraryDto;
import com.epam.library.entities.Library;
import com.epam.library.exceptions.BookNotIssuedException;
import com.epam.library.exceptions.MaxBooksIssuedException;
import com.epam.library.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Captor
    ArgumentCaptor<Library> libraryArgumentCaptor;
    @Mock
    LibraryRepository libraryRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    LibraryService libraryService;

    LibraryDto libraryDto;
    Library library;

    @BeforeEach
    void setUp() {
        library = new Library();
        library.setBookId(1);
        library.setUserName("Anupama");
        libraryDto = new LibraryDto();
        libraryDto.setBookId(1);
        libraryDto.setUserName("Anupama");
    }

    @Test
    void getInfoByUserName() {
        when(libraryRepository.findByUserName("Anupama")).thenReturn(List.of(library));
        when(modelMapper.map(List.of(library), new TypeToken<List<LibraryDto>>() {
        }.getType())).thenReturn(List.of(libraryDto));
        assertThat(libraryService.getInfoByUserName("Anupama")).isEqualTo(List.of(libraryDto));
    }

    @Test
    void deleteByUserName() {
        libraryService.deleteByUserName("Anupama");
        verify(libraryRepository).deleteByUserName("Anupama");
    }

    @Test
    void issueABookToAUser() {
        when(libraryRepository.findByUserName("Anupama")).thenReturn(List.of(library));
        when(libraryRepository.save(any(Library.class))).thenReturn(library);

        libraryService.issueABookToAUser("Anupama", 1);

        verify(libraryRepository).save(libraryArgumentCaptor.capture());

        Library argumentCaptorValue = libraryArgumentCaptor.getValue();

        assertThat(argumentCaptorValue.getBookId()).isEqualTo(1);
        assertThat(argumentCaptorValue.getUserName()).isEqualTo("Anupama");

        when(libraryRepository.findByUserName("Anupama")).thenReturn(List.of(library, library, library));
        assertThrows(MaxBooksIssuedException.class, () -> libraryService.issueABookToAUser("Anupama", 1));
    }

    @Test
    void releaseBookForAUSer() {

        when(libraryRepository.findByBookId(1)).thenReturn(Optional.ofNullable(library));
        libraryService.releaseBookForAUser("Anupama", 1);
        verify(libraryRepository).deleteByBookId(1);
        when(libraryRepository.findByBookId(1)).thenReturn(Optional.empty());
        assertThrows(BookNotIssuedException.class, () -> libraryService.releaseBookForAUser("Anupama", 1));

    }
}