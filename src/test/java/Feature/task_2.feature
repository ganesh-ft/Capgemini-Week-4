Feature: Ninza HRM Employee API Testing

  Background:
    Given Base URL is set
    And Content type is JSON

  Scenario Outline: End-to-End CRUD employee flow

    When I send POST request to create employee with "<name>" "<email>" "<phone>" "<designation>"
    Then response status should be 201
    And store employee details

    When I send GET request for stored employee
    Then response status should be 200
    And response field "empName" should be "<name>"
    And response field "email" should be "<email>"

    When I send PUT request to update employee with "<updatedName>"
    Then response status should be 200

    When I send DELETE request for stored employee
    Then response status should be 204

  Examples:
    | name      | email              | phone      | designation | updatedName |
    | mrlove_6  | mrlove@test.com   | 9123456780 | Dev         | updated01   |
    | killer_5  | killer@test.com   | 9988776655 | Manager     | updated02   |