Feature: Get All Books
  test get all books

  Scenario: We receive the books
    Given List with all books
    When request first book
    Then first book received