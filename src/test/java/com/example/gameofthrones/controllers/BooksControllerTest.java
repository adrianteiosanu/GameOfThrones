package com.example.gameofthrones.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.gameofthrones.rest.responses.Book;
import com.example.gameofthrones.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(BooksController.class)
public class BooksControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService booksService;

    @Test
    public void getBook() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book1 = objectMapper.readValue(new File("src/test/resources/book1.json"), Book.class);

        Mockito.when(booksService.getBookById("1")).thenReturn(book1);

        this.mvc.perform(get("/books/v1/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("A Game of Thrones")));
    }

    @Test
    public void getAllBooks() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book1 = objectMapper.readValue(new File("src/test/resources/book1.json"), Book.class);
        Book book2 = objectMapper.readValue(new File("src/test/resources/book1.json"), Book.class);
        List<Book> books= Arrays.asList(book1, book2);

        Mockito.when(booksService.getBooks()).thenReturn(books);

        this.mvc.perform(get("/books/v1")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].country", is("United States")))
                .andExpect(jsonPath("$.[1].publisher", is("Bantam Books")));
    }

    @Test
    public void getBooksWithFilter() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book1 = objectMapper.readValue(new File("src/test/resources/book1.json"), Book.class);
        List<Book> books= Arrays.asList(book1);

        Mockito.when(booksService.getBooksWithFilter("A Game of Thrones", null, null)).thenReturn(books);

        //A Game of Thrones
        this.mvc.perform(get("/books/v1/filter?name=A Game of Thrones")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name", is("A Game of Thrones")));
    }
}
