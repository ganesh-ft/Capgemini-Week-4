Feature: NINZA Project CRUD Operations using DataTable and POJO

  Scenario: Perform CRUD operations on multiple projects

    Given Base URI is set

    When I create projects with following data
      | createdBy | projectName | status  | teamSize |
      | myself_1    | Proj_1400     | Created | 0        |
      | myself_2  | Proj_2401     | Ongoing | 0        |

    Then response status should be 201

    When I get all created projects
    Then response status should be 200

    When I update all projects status to "Completed"
      | createdBy | projectName | status  | teamSize |
      | mrlove_01 | Proj_1900   | Ongoing | 0        |
      | mrlove_02 | Proj_2400   | Created | 0        |

    Then response status should be 200

    When I delete all created projects
    Then response status should be 204