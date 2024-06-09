Feature: Boundary tests for Categories API

  Scenario Outline: Retrieve category with maximum allowed depth
    Given I set the base URL
    When I send a GET request to "Categories.json?depth=<Depth Value>>"
    Then the response status code should be 200
    Examples:
      | Depth Value |
      | 0           |
      | 1           |
      | 2           |
      | -1          |
      | 99          |