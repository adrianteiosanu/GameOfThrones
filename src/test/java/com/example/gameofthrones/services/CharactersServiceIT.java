package com.example.gameofthrones.services;


import com.example.gameofthrones.rest.responses.Character;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CharactersServiceIT {

    @Autowired
    CharactersService charactersService;

    @Test
    public void getCharacterSuccess()
    {
        Character character = charactersService.getCharacterById("1");
        assertNotNull(character);
        assertEquals(character.getCulture(), "Braavosi");
    }

    @Test
    public void getAllCharacters()
    {
        List<Character> characters = charactersService.getCharacters();
        assertNotNull(characters);
        assertEquals(characters.size(), 10);
    }

    @Test
    public void getCharactersWithPagination()
    {
        List<Character> characters = charactersService.getCharactersByPage(1, 20);
        assertNotNull(characters);
        assertEquals(characters.size(), 20);
    }

    @Test
    public void getCharactersWithFilter()
    {
        List<Character> characters = charactersService.getCharactersWithFilter("Walder", "Male", "", "", "",null);
        assertNotNull(characters);
        assertEquals(characters.size(), 1);
    }
}
