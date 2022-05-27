package com.example.gameofthrones.rest.responses;

import lombok.Data;

import java.util.List;

@Data
public class House {
    private String url;
    private String name;
    private String region;
    private String coatOfArms;
    private String words;
    private List<String> titles;
    private List<String> seats;
    private String currentLord;
    private String heir;
    private String overlord; // Long id of house
    private String founded;
    private String founder; // Long id of characters
    private String diedOut;
    private List<String> ancestralWeapons;
    private List<String> cadetBranches; // List<Long> ids of houses
    private List<String> swornMembers; // List<Long> ids of characters
}
