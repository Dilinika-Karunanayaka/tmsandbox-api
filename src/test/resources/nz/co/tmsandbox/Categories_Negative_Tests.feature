Feature: Negative tests for Categories API

  Scenario Outline: Validate HTTP Method <Method> for Category endpoint
    Given I set the base URL
    When I send a "<Method>" request to "/Categories/0001.json"
    Then the response status code should be <Status Code>
    Examples:
      | Method | Status Code |
      | POST   | 405         |
      | PUT    | 405         |
      | PATCH  | 405         |
      | DELETE | 405         |

  Scenario Outline: Retrieve an special character category number
    Given I set the base URL
    When I send a GET request to "<Request>"
    Then the response status code should be <Status Code>
    Examples:
      | Request                                        | Status Code |
      | /Categories/.json                              | 404         |
      | /Categories/****.json                          | 404         |
      | /Categories/999999.json                        | 400         |
      | /Categories.json?depth=-1                      | 200         |
      | /Categories.json?with_counts=true&region=99999 | 400         |
      | /Categories/1.csv                              | 404         |