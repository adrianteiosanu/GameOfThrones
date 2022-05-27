package com.example.gameofthrones.controllers;

import com.example.gameofthrones.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books/v1")
@RequiredArgsConstructor
public class BooksController {

    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity readBookData(@RequestParam(required = false) String bookId,
                                       @RequestParam(required = false) Integer page,
                                       @RequestParam(required = false) Integer pageSize)
    {
        if(bookId != null)
        {
            return ResponseEntity.ok(bookService.getBookById(bookId));
        }
        else if (page != null && pageSize != null)
        {
            return ResponseEntity.ok(bookService.getBooksByPage(page, pageSize));
        }
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity readBook(@PathVariable String bookId)
    {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/filter")
    public ResponseEntity readBooksWithFilter(@RequestParam(required = false) Optional<String> name,
                                              @RequestParam(required = false) Optional<Date> fromReleaseDate,
                                              @RequestParam(required = false) Optional<Date> toReleaseDate)
    {
        return ResponseEntity.ok(bookService.getBooksWithFilter(name.orElse(""),
                                                                fromReleaseDate.orElse(null),
                                                                toReleaseDate.orElse(null)));
    }
}
