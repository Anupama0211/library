package com.epam.users.services;

import com.epam.users.dtos.UserDto;
import com.epam.users.entities.User;
import com.epam.users.exceptions.UserAlreadyPresentException;
import com.epam.users.exceptions.UserNotFoundException;
import com.epam.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    ModelMapper modelMapper;
    @InjectMocks
    UserService userService;
    UserDto userDto;
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserName("Anu02");
        user.setEmail("jhaanupama0211@gmail.com");
        user.setName("Anupama");

        userDto = new UserDto();
        userDto.setUserName("Anu02");
        userDto.setEmail("jhaanupama0211@gmail.com");
        userDto.setName("Anupama");
    }

    @Test
    void getAllUSers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(modelMapper.map(List.of(user), new TypeToken<List<UserDto>>() {
        }.getType())).thenReturn(List.of(userDto));
        assertThat(userService.getAllUsers()).isEqualTo(List.of(userDto));
        when(userRepository.findAll()).thenReturn(List.of());
        assertThrows(UserNotFoundException.class, () -> userService.getAllUsers());

    }

    @Test
    void addUser() {
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        assertThat(userService.addUser(userDto)).isEqualTo(userDto);
        verify(userRepository).save(user);
        when(userRepository.findById(userDto.getUserName())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserAlreadyPresentException.class, () -> userService.addUser(userDto));
    }

    @Test
    void removeUser() {
        userService.removeUser("Anu02");
        verify(userRepository).deleteById("Anu02");
    }

    @Test
    void getUserById() {
        when(userRepository.findById("Anu02")).thenReturn(Optional.ofNullable(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        assertThat(userService.getUserById("Anu02")).isEqualTo(userDto);
        when(userRepository.findById("Anu02")).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById("Anu02"));
    }


    @Test
    void updateUser() {

        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userRepository.findById("Anu02")).thenReturn(Optional.ofNullable(user));
        assertThat(userService.updateUser(userDto, "Anu02")).isEqualTo(userDto);
        verify(userRepository).save(user);
        when(userRepository.findById("Anu02")).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userDto, "Anu02"));
        user.setUserName("Anupama");
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userDto, "Anu02"));
    }
}