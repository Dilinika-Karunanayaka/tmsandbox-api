Feature: Retrieve Categories API

  Scenario Outline: Get details of category <Categories>
    Given I set the base URI
    When I send a GET request to "/Categories/<Categories>.json"
    Then the response status code should be 200
    And the response should have the following fields
      | Name                  |
      | Number                |
      | Path                  |
      | Subcategories         |
      | AreaOfBusiness        |
      | IsLeaf                |
      | AreaOfBusiness        |
      | CanHaveSecondCategory |

  Examples:
  |Categories|
  |001|
  |002|
  |003|