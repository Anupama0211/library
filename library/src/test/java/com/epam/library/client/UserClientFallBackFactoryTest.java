package com.epam.library.client;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserClientFallBackFactoryTest {

    @Test
    void create() {
        UserClientFallBackFactory userClientFallBackFactory = new UserClientFallBackFactory();
        assertThat(userClientFallBackFactory.create(new Throwable())).isInstanceOf(UserClientImpl.class);
    }
}