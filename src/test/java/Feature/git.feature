Feature: GitHub Repository CRUD Operations

  Scenario Outline: End-to-End GitHub CRUD flow
    Given GitHub base URI is set
    And Authorization token is provided

    When I create repository with name "<repo>"
    Then response status should be 201

    When I get repository "<repo>"
    Then response status should be 200

    When I update repository "<repo>"
    Then response status should be 200

    When I delete repository "<repo>"
    Then response status should be 204
    

  Examples:
    | repo        | 
    | demo-repo71111fg1111 |