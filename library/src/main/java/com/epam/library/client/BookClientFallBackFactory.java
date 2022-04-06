package com.epam.library.client;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class BookClientFallBackFactory implements FallbackFactory<BookClient> {
    @Override
    public BookClient create(Throwable cause) {
        return new BookClientImpl(cause);
    }
}
