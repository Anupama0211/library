package com.epam.library.client;

import com.epam.library.dtos.UserDto;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;

@Component
public class UserClientImpl implements UserClient{
    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto getAUser(String userName) {
        return new UserDto();
    }

    @Override
    public UserDto addAUser(UserDto userDto) {
        return null;
    }

    @Override
    public void removeAUser(String userName) {

    }

    @Override
    public UserDto updateAUser(UserDto userDto, String userName) {
        return null;
    }
}
