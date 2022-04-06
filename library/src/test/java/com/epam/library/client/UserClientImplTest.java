package com.epam.library.client;


import com.epam.library.dtos.UserDto;
import com.epam.library.exceptions.OperationNotPerformedException;
import com.epam.library.exceptions.SampleFeingException;
import feign.FeignException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class UserClientImplTest {

    UserClientImpl userClient = new UserClientImpl(new Throwable());
    UserDto userDto = new UserDto();

    @Test
    void getAllUsers() {
        assertThat(userClient.getAllUsers().get(0)).isInstanceOf(UserDto.class);
        userClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> userClient.getAllUsers());
    }

    @Test
    void getAUser() {
        assertThat(userClient.getAUser("Anu")).isInstanceOf(UserDto.class);
        userClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> userClient.getAUser("Anu"));
    }

    @Test
    void addAUser() {
        assertThrows(OperationNotPerformedException.class, () -> userClient.addAUser(userDto));
        userClient.setCause(new SampleFeingException(400, ""));
        assertThrows(FeignException.class, () -> userClient.addAUser(userDto));
    }

    @Test
    void removeAUser() {
        assertThrows(OperationNotPerformedException.class, () -> userClient.removeAUser("Anu"));
        userClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> userClient.removeAUser("Anu"));

    }

    @Test
    void updateAUser() {
        assertThrows(OperationNotPerformedException.class, () -> userClient.updateAUser(userDto, "Anu"));
        userClient.setCause(new SampleFeingException(404, ""));
        assertThrows(FeignException.class, () -> userClient.updateAUser(userDto, "Anu"));
    }
}