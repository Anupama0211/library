package com.epam.library.repository;


import com.epam.library.entities.Library;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface LibraryRepository extends CrudRepository<Library, Integer> {

    List<Library> findByUserName(String userName);

    @Transactional
    void deleteByUserName(String userName);

    @Transactional
    void deleteByBookId(int bookId);

    Optional<Library> findByBookId(int bookId);
}
