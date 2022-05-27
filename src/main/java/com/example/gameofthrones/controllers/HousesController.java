package com.example.gameofthrones.controllers;

import com.example.gameofthrones.services.HousesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/houses/v1")
@RequiredArgsConstructor
public class HousesController {

    @Autowired
    HousesService houseService;

    @GetMapping
    public ResponseEntity readHouseData(@RequestParam(required = false) String houseId,
                                        @RequestParam(required = false) Integer page,
                                        @RequestParam(required = false) Integer pageSize)
    {
        if(houseId != null)
        {
            return ResponseEntity.ok(houseService.getHouseById(houseId));

        }
        else if (page != null && pageSize != null)
        {
            return ResponseEntity.ok(houseService.readHousesByPage(page, pageSize));
        }
        return ResponseEntity.ok(houseService.getHouses());
    }

    @GetMapping("/{houseId}")
    public ResponseEntity readBook(@PathVariable String houseId)
    {
        return ResponseEntity.ok(houseService.getHouseById(houseId));
    }

    @GetMapping("/filter")
    public ResponseEntity readHousesWithFilter(@RequestParam(required = false) Optional<String> name,
                                               @RequestParam(required = false) Optional<String> region,
                                               @RequestParam(required = false) Optional<String> words,
                                               @RequestParam(required = false) Optional<Boolean> hasWords,
                                               @RequestParam(required = false) Optional<Boolean> hasTitles,
                                               @RequestParam(required = false) Optional<Boolean> hasSeats,
                                               @RequestParam(required = false) Optional<Boolean> hasDiedOut,
                                               @RequestParam(required = false) Optional<Boolean> hasAncestralWeapons)
    {
        return ResponseEntity.ok(houseService.readHousesWithFilter(name.orElse(""),
                                                                    region.orElse(""),
                                                                    words.orElse(""),
                                                                    hasWords.orElse(null),
                                                                    hasTitles.orElse(null),
                                                                    hasSeats.orElse(null),
                                                                    hasDiedOut.orElse(null),
                                                                    hasAncestralWeapons.orElse(null)));
    }
}
