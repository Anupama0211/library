package com.epam.books.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "books")
public class Book {
    @Id
    int id;
    String name;
    String publisher;
    String author;
}
