package com.example.gameofthrones.services;

import com.example.gameofthrones.rest.responses.Book;
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
public class BooksServiceIT {

    @Autowired
    BookService booksService;

    @Test
    public void getBookSuccess()
    {
        Book book = booksService.getBookById("1");
        assertNotNull(book);
        assertEquals(book.getIsbn(), "978-0553103540");
    }

    @Test
    public void getAllBooks()
    {
        List<Book> books = booksService.getBooks();
        assertNotNull(books);
        assertEquals(books.size(), 10);
    }

    @Test
    public void getBooksWithPagination()
    {
        List<Book> books = booksService.getBooksByPage(1, 5);
        assertNotNull(books);
        assertEquals(books.size(), 5);
    }

    @Test
    public void getBooksWithFilter()
    {
        List<Book> books = booksService.getBooksWithFilter("A Game of Thrones", null, null);
        assertEquals(books.size(), 1);
    }
}
