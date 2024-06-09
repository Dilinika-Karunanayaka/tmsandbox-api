Feature: Performance and Security tests for Categories API

  Scenario: Measure response time for retrieving top-level categories
    Given I set the base URL
    When I send a GET request to "Categories.json"
    Then the response status code should be 200
    And the response time should be less than 500 milliseconds

  Scenario: Measure response time for retrieving a category with many subcategories
    Given I set the base URL
    When I send a GET request to "Categories/0001.json"
    Then the response status code should be 200
    And the response time should be less than 300 milliseconds

  Scenario: Retrieve an special character category number
    Given I set the base URL
    When I send a GET request to "/Categories/*.json"
    Then the response status code should be 404
    Then the response should not contain sensitive information