package com.example.gameofthrones.rest.responses;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Book {
    private String url;
    private String name;
    private String isbn;
    private List<String> authors;
    private Long numberOfPages;
    private String publisher;
    private String country;
    private String mediaType;
    private Date released;
    private List<String> characters;
    private List<String> povCharacters;
}
