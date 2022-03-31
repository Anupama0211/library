package com.epam.library.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class LibraryDto {
    int id;
    @Size(min = 5, message = "UserName should be above 4 characters length")
    String userName;
    int bookId;
}
