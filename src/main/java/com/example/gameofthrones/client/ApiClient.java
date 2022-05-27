package com.example.gameofthrones.client;

import com.example.gameofthrones.rest.responses.Book;
import com.example.gameofthrones.rest.responses.Character;
import com.example.gameofthrones.rest.responses.House;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@FeignClient(value = "${api.config.name}", url = "${api.config.url}")
public interface ApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    List<Book> readBooks();

    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}")
    Book readBookById(@PathVariable("bookId") String bookId);

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    List<Book> readBooksByPage(@RequestParam Integer page, @RequestParam Integer pageSize);

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    List<Book> readBooksWithFilter(@RequestParam String name,
                                    @RequestParam Date fromReleaseDate,
                                    @RequestParam Date toReleaseDate);

    @RequestMapping(method = RequestMethod.GET, value = "/houses")
    List<House> readHouses();

    @RequestMapping(method = RequestMethod.GET, value = "/houses/{houseId}")
    House readHouseById(@PathVariable("houseId") String houseId);

    @RequestMapping(method = RequestMethod.GET, value = "/houses")
    List<House> readHousesByPage(@RequestParam Integer page, @RequestParam Integer pageSize);

    @RequestMapping(method = RequestMethod.GET, value = "/houses")
    List<House> readHousesWithFilter(@RequestParam String name,
                                     @RequestParam String region,
                                     @RequestParam String words,
                                     @RequestParam Boolean hasWords,
                                     @RequestParam Boolean hasTitles,
                                     @RequestParam Boolean hasSeats,
                                     @RequestParam Boolean hasDiedOut,
                                     @RequestParam Boolean hasAncestralWeapons);

    @RequestMapping(method = RequestMethod.GET, value = "/characters")
    List<Character> readCharacters();

    @RequestMapping(method = RequestMethod.GET, value = "/characters/{characterId}")
    Character readCharacterById(@PathVariable("characterId") String characterId);

    @RequestMapping(method = RequestMethod.GET, value = "/characters")
    List<Character> readCharactersByPage(@RequestParam Integer page, @RequestParam Integer pageSize);

    @RequestMapping(method = RequestMethod.GET, value = "/characters")
    List<Character> readCharactersWithFilter(@RequestParam String name,
                                             @RequestParam String gender,
                                             @RequestParam String culture,
                                             @RequestParam String born,
                                             @RequestParam String died,
                                             @RequestParam Boolean isAlive);
}