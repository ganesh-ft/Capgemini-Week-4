# Author: mrlove

Feature: Post Scenario Outline on Ninza project

Scenario Outline: Create multiple new projects
  Given Base URL is set
  When I send a POST request to create a project with "<createdBy>" "<projectName>" "<status>" <teamSize>
  Then response status should be <statusCode>
  And response should contain "<message>"

Examples:
  | createdBy | projectName   | status   | teamSize | statusCode | message  |
  | NoteA     | Proj_012      | Created  | 0        | 201        | Created  |
  | NoteB     | Proj_023      | Created  | 0        | 201        | Created  |
  | NoteC     | Proj_034      | Created  | 0        | 201        | Created  |
  | NoteD     | Proj_045     | Created  | 0        | 201        | Created  |
  | NoteE     | Proj_056      | Created  | 0        | 201        | Created  |