package com.epam.library.client;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class BookClientFallBackFactoryTest {

    @Test
    void create() {
        BookClientFallBackFactory bookClientFallBackFactory = new BookClientFallBackFactory();
        assertThat(bookClientFallBackFactory.create(new Throwable())).isInstanceOf(BookClientImpl.class);
    }
}