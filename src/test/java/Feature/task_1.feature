#Author mrlove

Feature: CRUD operations on petstore

Background:
  Given the petstore API is available

Scenario: Create a new pet
  When I send a POST request to create a pet
  Then the pet should be created successfully with status code 200

Scenario: Get pet details
  When I send a POST request to create a pet
  And I send a GET request to fetch pet details
  Then the pet details should be returned with status code 200

Scenario: Update pet details
  When I send a POST request to create a pet
  And I send a PUT request to update the pet
  Then the pet details should be updated successfully with status code 200

Scenario: Delete a pet
  When I send a POST request to create a pet
  And I send a DELETE request to remove the pet
  Then the pet should be deleted successfully with status code 200