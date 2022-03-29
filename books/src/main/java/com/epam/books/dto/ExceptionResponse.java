package com.epam.books.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ExceptionResponse {
    String timestamp;
    String error;
    String status;
    String path;
}
