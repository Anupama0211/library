package com.epam.library.client;


import com.epam.library.dtos.BookDto;
import com.epam.library.exceptions.OperationNotPerformedException;
import com.epam.library.exceptions.SampleFeingException;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BookClientImplTest {
    BookClientImpl bookClient = new BookClientImpl(new Throwable());
    BookDto bookDto=new BookDto();

    @Test
    void getAllBooks() {
        assertThat(bookClient.getAllBooks().get(0)).isInstanceOf(BookDto.class);
        bookClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> bookClient.getAllBooks());
    }

    @Test
    void getABook() {
        assertThat(bookClient.getABook(1)).isInstanceOf(BookDto.class);
        bookClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> bookClient.getABook(1));
    }

    @Test
    void addABook() {
        assertThrows(OperationNotPerformedException.class, () -> bookClient.addABook(bookDto));
        bookClient.setCause(new SampleFeingException(400, ""));
        assertThrows(FeignException.class, () -> bookClient.addABook(bookDto));
    }

    @Test
    void removeABook() {
        assertThrows(OperationNotPerformedException.class, () -> bookClient.removeABook(1));
        bookClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> bookClient.removeABook(1));

    }

    @Test
    void updateABook() {
        assertThrows(OperationNotPerformedException.class, () -> bookClient.updateABook(bookDto, 1));
        bookClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> bookClient.updateABook(bookDto, 1));

    }
}