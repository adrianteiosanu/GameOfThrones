package com.example.gameofthrones.rest.responses;

import lombok.Data;

import java.util.List;

@Data
public class Character {
    private String url; // Long id
    private String name;
    private String gender;
    private String culture;
    private String born;
    private String died;
    private List<String> titles;
    private List<String> aliases;
    private String father;
    private String mother;
    private String spouse;
    private List<String> allegiances; // List<Long> ids of houses
    private List<String> books; // List<Long> ids of books
    private List<String> povBooks;
    private List<String> tvSeries;
    private List<String> playedBy;
}
