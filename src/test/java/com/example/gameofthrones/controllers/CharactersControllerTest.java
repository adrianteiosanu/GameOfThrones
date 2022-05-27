package com.example.gameofthrones.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.gameofthrones.rest.responses.Character;
import com.example.gameofthrones.services.CharactersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(CharactersController.class)
public class CharactersControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CharactersService charactersService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getCharacter() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Character character1 = objectMapper.readValue(new File("src/test/resources/character1.json"), Character.class);

        Mockito.when(charactersService.getCharacterById("1")).thenReturn(character1);

        this.mvc.perform(get("/characters/v1/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.gender", is("Female")));
    }

    @Test
    public void getAllCharacters() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Character character1 = objectMapper.readValue(new File("src/test/resources/character1.json"), Character.class);
        Character character2 = objectMapper.readValue(new File("src/test/resources/character2.json"), Character.class);
        List<Character> characters= Arrays.asList(character1, character2);

        Mockito.when(charactersService.getCharacters()).thenReturn(characters);

        this.mvc.perform(get("/characters/v1")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].gender", is("Female")))
                .andExpect(jsonPath("$.[1].name", is("Walder")));
    }

    @Test
    public void getCharactersWithFilter() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Character character2 = objectMapper.readValue(new File("src/test/resources/character2.json"), Character.class);
        List<Character> characters= Arrays.asList(character2);

        Mockito.when(charactersService.getCharactersWithFilter("Walder", "Male", "", "", "",null))
                .thenReturn(characters);

        this.mvc.perform(get("/characters/v1/filter?name=Walder&gender=Male")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name", is("Walder")))
                .andExpect(jsonPath("$.[0].gender", is("Male")));
    }
}
