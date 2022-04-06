package com.epam.library.client;

import com.epam.library.dtos.UserDto;
import com.epam.library.exceptions.OperationNotPerformedException;
import feign.FeignException;
import lombok.Setter;

import java.util.List;

public class UserClientImpl implements UserClient {

    private static final String MESSAGE = "Operation Cannot be performed";
    private @Setter
    Throwable cause;

    public UserClientImpl(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<UserDto> getAllUsers() {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        return List.of(new UserDto());
    }

    @Override
    public UserDto getAUser(String userName) {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        return new UserDto();
    }

    @Override
    public UserDto addAUser(UserDto userDto) {
        if (cause instanceof FeignException feignException && feignException.status() == 400) {
            throw feignException;
        }
        throw new OperationNotPerformedException(MESSAGE);
    }

    @Override
    public void removeAUser(String userName) {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        throw new OperationNotPerformedException(MESSAGE);
    }

    @Override
    public UserDto updateAUser(UserDto userDto, String userName) {
        if (cause instanceof FeignException feignException && feignException.status() == 404) {
            throw feignException;
        }
        throw new OperationNotPerformedException(MESSAGE);
    }
}
