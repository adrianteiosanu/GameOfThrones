package com.example.gameofthrones.controllers;

import com.example.gameofthrones.services.CharactersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/characters/v1")
@RequiredArgsConstructor
public class CharactersController {
    
    @Autowired
    CharactersService charactersService;

    @GetMapping
    public ResponseEntity readCharacterData(@RequestParam(required = false) String characterId,
                                            @RequestParam(required = false) Integer page,
                                            @RequestParam(required = false) Integer pageSize)
    {
        if (characterId != null)
        {
            return ResponseEntity.ok(charactersService.getCharacterById(characterId));
        }
        else if (page != null && pageSize != null)
        {
            return ResponseEntity.ok(charactersService.getCharactersByPage(page, pageSize));
        }

        return ResponseEntity.ok(charactersService.getCharacters());

    }

    @GetMapping("/{characterId}")
    public ResponseEntity readBook(@PathVariable String characterId)
    {
        return ResponseEntity.ok(charactersService.getCharacterById(characterId));
    }

    @GetMapping("/filter")
    public ResponseEntity readCharactersWithFilter(@RequestParam(required = false) Optional<String> name,
                                                   @RequestParam(required = false) Optional<String> gender,
                                                   @RequestParam(required = false) Optional<String> culture,
                                                   @RequestParam(required = false) Optional<String> born,
                                                   @RequestParam(required = false) Optional<String> died,
                                                   @RequestParam(required = false) Optional<Boolean> isAlive)
    {
        return ResponseEntity.ok(charactersService.getCharactersWithFilter(name.orElse(""),
                                                                            gender.orElse(""),
                                                                            culture.orElse(""),
                                                                            born.orElse(""),
                                                                            died.orElse(""),
                                                                            isAlive.orElse(null)));
    }
}