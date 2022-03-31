package com.epam.users.restcontrollers;

import com.epam.users.dtos.UserDto;
import com.epam.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<List<UserDto>> viewUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("{username}")
    ResponseEntity<UserDto> viewUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserById(username), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<UserDto> addAUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{username}")
    ResponseEntity<HttpStatus> deleteAUser(@PathVariable String username) {
        userService.removeUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{username}")
    ResponseEntity<UserDto> updateAUser(@PathVariable String username, @RequestBody @Valid UserDto userDto) {

        return new ResponseEntity<>(userService.updateUser(userDto, username), HttpStatus.OK);
    }

}
