package com.epam.users.dtos;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {
    @NotEmpty
    @Size(min = 5, message = "UserName should be above 4 characters length")
    String userName;
    @NotEmpty
    @Size(min = 10, message = "Email should be above 4 characters length")
    String email;
    @NotEmpty
    @Size(min = 5, message = "Name should be above 4 characters length")
    String name;
}
