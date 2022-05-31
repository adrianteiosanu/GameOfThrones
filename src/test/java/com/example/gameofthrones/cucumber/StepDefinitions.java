package com.example.gameofthrones.cucumber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.gameofthrones.client.ApiClient;
import com.example.gameofthrones.rest.responses.Book;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class StepDefinitions {

    @Autowired
    private ApiClient apiClient;

    @Given("^List with all books$")
    public void list_with_all_books()
    {
        List<Book> books = apiClient.readBooks();
        assert( books.size() > 0);
    }

    @When("^request first book$")
    public void request_first_book( ) throws Throwable {
        Book book = apiClient.readBookById( "1");
        assertNotNull(book);
    }

    @Then("^first book received$")
    public void receive_first_book() throws Throwable {
        List<Book> books = apiClient.readBooks();
        Book book = apiClient.readBookById( "1" );
        Optional<Book> optionalBook = books.stream().filter( b -> b.getIsbn().compareTo(book.getIsbn()) == 0).findFirst();
        assertTrue(optionalBook.isPresent());
    }
}
