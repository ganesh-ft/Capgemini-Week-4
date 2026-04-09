#Author mrlove

Feature: CRUD operations on FakeRest API

Background:
  Given: Base URI is set for fakerest

Scenario: Create a new book
  When I send POST request to create a book
  Then fakerest status should be 200

Scenario: Get book details
  When I send POST request to create a book
  And I send GET request to fetch book
  Then fakerest status should be 200

Scenario: Update book details
  When I send POST request to create a book
  And I send PUT request to update book
  Then fakerest status should be 200

Scenario: Delete a book
  When I send POST request to create a book
  And I send DELETE request to remove book
  Then fakerest status should be 200