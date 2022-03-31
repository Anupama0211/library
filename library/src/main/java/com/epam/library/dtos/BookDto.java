package com.epam.library.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
public class BookDto {
    int id;
    @NotEmpty
    @Size(min = 5, message = "BookName should be above 4 characters length")
    String name;
    @NotEmpty
    @Size(min = 5, message = "Publisher should be above 4 characters length")
    String publisher;
    @NotEmpty
    @Size(min = 5, message = "Author should be above 4 characters length")
    String author;

}
