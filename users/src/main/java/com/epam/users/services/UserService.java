package com.epam.users.services;

import com.epam.users.dtos.UserDto;
import com.epam.users.entities.User;
import com.epam.users.exceptions.UserAlreadyPresentException;
import com.epam.users.exceptions.UserNotFoundException;
import com.epam.users.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<UserDto> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users Present!!!");
        }
        return modelMapper.map(users, new TypeToken<List<UserDto>>() {
        }.getType());
    }

    public UserDto addUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getUserName());
        if (userOptional.isPresent()) {
            throw new UserAlreadyPresentException("User name is already used!");
        }
        User user = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(user, UserDto.class);
    }

    public void removeUser(String userName) {
        userRepository.deleteById(userName);
    }

    public UserDto getUserById(String userName) {
        return modelMapper
                .map(userRepository
                                .findById(userName)
                                .orElseThrow(() -> new UserNotFoundException("Invalid Username!!"))
                        , UserDto.class);
    }

    public UserDto updateUser(UserDto userDto, String userName) {
        Optional<User> userOptional = userRepository.findById(userName);
        if (userOptional.isPresent() && userDto.getUserName().equals(userName)) {
            userDto.setUserName(userName);
            User user = userRepository.save(modelMapper.map(userDto, User.class));
            return modelMapper.map(user, UserDto.class);
        } else {
            throw new UserNotFoundException("Invalid Username!!");
        }
    }
}
