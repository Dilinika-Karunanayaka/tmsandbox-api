Feature: Positve tests for Categories API

  Scenario: Retrieve Root category
    Given I set the base URI
    When I send a GET request to "/Categories.json"
    Then the response status code should be 200
    And the response should contain below values in root
      | Name           | Root  |
      | Number         |       |
      | Path           |       |
      | Subcategories  | []    |
      | AreaOfBusiness | 0     |
      | IsLeaf         | false |

  Scenario: Validate Trade Me Motor Category
    Given I set the base URI
    When I send a GET request to "/Categories/0001.json"
    Then the response status code should be 200
    And the response should have the following fields
      | Name                  |
      | Number                |
      | Path                  |
      | Subcategories         |
      | HasClassifieds        |
      | AreaOfBusiness        |
      | CanHaveSecondCategory |
      | CanBeSecondCategory   |
      | IsLeaf                |
    And the response should contain a valid data types
    And the response should contains below values
      | Name                  | Trade Me Motors  |
      | Number                | 0001-            |
      | Path                  | /Trade-Me-Motors |
      | Subcategories         | []               |
      | HasClassifieds        | true             |
      | AreaOfBusiness        | 3                |
      | CanHaveSecondCategory | true             |
      | CanBeSecondCategory   | true             |
      | IsLeaf                | false            |

  Scenario: Retrieve categories with zero depth value
    Given I set the base URI
    When I send a GET request to "Categories.json?depth=0"
    Then the response status code should be 200
    And the response should not contain a field "Subcategories"

  Scenario Outline: Retrieve categories with valid depth values
    Given I set the base URI
    When I send a GET request to "Categories.json?depth=<Depth Value>"
    Then the response status code should be 200
    And the response should contain a field "Subcategories"
    Examples:
      | Depth Value |
      | 1           |
      | 2           |

  Scenario Outline: Retrieve categories with <Format> file format
    Given I set the base URI
    When I send a GET request to "/Categories/0001.<Format>"
    Then the response status code should be 200
    And the response format should be "<format>"
    Examples:
      | Format |
      | json   |
      | xml    |

  Scenario Outline: Retrieve number of items for sale in <Category>
    Given I set the base URI
    When I send a GET request to "Categories/<Number>.json?with_counts=true"
    Then the response status code should be 200
    And the response should contain a field "Count"

    Examples:
      | Category | Number |
      | Motor    | 0001   |
      | Job      | 5000   |
      | Property | 0350   |

  Scenario Outline: Retrieve number of items for sale in a region
    Given I set the base URI
    When I send a GET request to "Categories/<Number>.json?with_counts=true&region=2"
    Then the response status code should be 200
    And the response should contain a field "Count"
    Examples:
      | Category | Number |
      | Motor    | 0001   |
      | Job      | 5000   |
      | Property | 0350   |