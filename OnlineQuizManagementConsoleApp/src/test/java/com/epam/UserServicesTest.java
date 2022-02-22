package com.epam;

import com.epam.services.userservices.RegisterUser;
import com.epam.services.userservices.UserServicesFactory;
import com.epam.services.userservices.ValidateUserCredentials;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {
    @Test
    void registerUserTest() {
        assertFalse(new RegisterUser().perform("Anupama", "1234", 3));
        assertTrue(new RegisterUser().perform("X", "1234", 3));
        assertTrue(new RegisterUser().perform("Y", ",1234", 4));

    }

    @Test
    void validateUserCredentialsTest() {
        assertTrue(new ValidateUserCredentials().perform("Anupama", "abcdef", 1));
        assertFalse(new ValidateUserCredentials().perform("Anupama", "abcdef", 2));
        assertFalse(new ValidateUserCredentials().perform("Anupama", "adef", 1));
        assertFalse(new ValidateUserCredentials().perform("A", "abcdef", 1));
    }

    @Test
    void userServiceFactoryTest() {
        assertInstanceOf(ValidateUserCredentials.class, new UserServicesFactory().getUserservice(1).get());
        assertInstanceOf(ValidateUserCredentials.class, new UserServicesFactory().getUserservice(2).get());
        assertInstanceOf(RegisterUser.class, new UserServicesFactory().getUserservice(3).get());
        assertInstanceOf(RegisterUser.class, new UserServicesFactory().getUserservice(4).get());
        assertInstanceOf(Optional.class, new UserServicesFactory().getUserservice(7));

    }
}
