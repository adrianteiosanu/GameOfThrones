package com.example.gameofthrones.services;

import com.example.gameofthrones.client.ApiClient;
import com.example.gameofthrones.rest.responses.Character;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersService {
    private final ApiClient apiClient;

    public CharactersService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Character> getCharacters()
    {
        return this.apiClient.readCharacters();
    }

    public Character getCharacterById(String characterId)
    {
        return this.apiClient.readCharacterById(characterId);
    }

    public List<Character> getCharactersByPage(int page, int pageSize)
    {
        return apiClient.readCharactersByPage(page, pageSize);
    }

    public List<Character> getCharactersWithFilter(String name, String gender, String culture, String born, String died, Boolean isAlive)
    {
        return apiClient.readCharactersWithFilter(name, gender, culture, born, died, isAlive);
    }
}
