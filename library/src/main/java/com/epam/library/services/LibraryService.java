package com.epam.library.services;

import com.epam.library.dtos.LibraryDto;
import com.epam.library.entities.Library;
import com.epam.library.exceptions.BookNotIssuedException;
import com.epam.library.exceptions.MaxBooksIssuedException;
import com.epam.library.repository.LibraryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository libraryRepository;
    @Autowired
    ModelMapper modelMapper;
    static final int MAXLIMT = 3;

    public List<LibraryDto> getInfoByUserName(String userName) {
        return modelMapper
                .map(libraryRepository
                        .findByUserName(userName), new TypeToken<List<LibraryDto>>() {
                }.getType());
    }

    public void deleteByUserName(String userName) {
        libraryRepository.deleteByUserName(userName);
    }

    public LibraryDto issueABookToAUser(String userName, int bookId) {
        Library library = new Library();
        library.setBookId(bookId);
        library.setUserName(userName);
        if (libraryRepository.findByUserName(userName).size() < MAXLIMT) {
            return modelMapper.map(libraryRepository.save(library), LibraryDto.class);
        } else {
            throw new MaxBooksIssuedException("You cannot issue more than " + MAXLIMT + " books");
        }
    }

    public void releaseBookForAUser(String username, int bookId) {
        Optional<Library> libraryOptional=libraryRepository.findByBookId(bookId);
        if(libraryOptional.isEmpty()){
            throw new BookNotIssuedException("Book Id "+bookId+" has not been issued to "+username);
        }
        libraryRepository.deleteByBookId(bookId);
    }

}
