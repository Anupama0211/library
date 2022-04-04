package com.epam.users.restcontrollers;

import com.epam.users.dtos.UserDto;
import com.epam.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(description = "It gets all the users")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    ResponseEntity<List<UserDto>> viewUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(description = "It gets a user based on the username")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping("{username}")
    ResponseEntity<UserDto> viewUser(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserById(username), HttpStatus.OK);
    }

    @Operation(description = "It creates a user")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "201", description = "Created")
    @PostMapping
    ResponseEntity<UserDto> addAUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    @Operation(description = "It deletes a user")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "204", description = "No Content")
    @DeleteMapping("{username}")
    ResponseEntity<HttpStatus> deleteAUser(@PathVariable String username) {
        userService.removeUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Operation(description = "It updates a user")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "200", description = "OK")
    @PutMapping("{username}")
    ResponseEntity<UserDto> updateAUser(@PathVariable String username, @RequestBody @Valid UserDto userDto) {

        return new ResponseEntity<>(userService.updateUser(userDto, username), HttpStatus.OK);
    }

}
