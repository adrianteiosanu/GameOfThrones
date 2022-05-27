package com.example.gameofthrones.services;

import com.example.gameofthrones.client.ApiClient;
import com.example.gameofthrones.rest.responses.Book;
import com.example.gameofthrones.rest.responses.Character;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BookService {

    private final ApiClient apiClient;

    public BookService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Book> getBooks()
    {
        return this.apiClient.readBooks();
    }

    public Book getBookById(String bookId)
    {
        return this.apiClient.readBookById(bookId);
    }

    public List<Book> getBooksByPage(int page, int pageSize)
    {
        return apiClient.readBooksByPage(page, pageSize);
    }

    public List<Book> getBooksWithFilter(String name, Date fromReleaseDate, Date toReleaseDate)
    {
        return apiClient.readBooksWithFilter(name, fromReleaseDate, toReleaseDate);
    }
}
