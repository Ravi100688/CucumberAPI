Feature: Validation place APIs

  @AddPlace @Regression
  Scenario: Verify place is added successfully by using the APIs
    Given Add place playload
    When User call "AddPlaceAPI" with "Post" http request
    Then The API call get success With status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    
  @AddPlace @Regression
  Scenario Outline: Verify place is added successfully by using the APIs
    Given Add place playload with "<name>" "<language>" "<address>"
    When User call "AddPlaceAPI" with "Post" http request
    Then The API call get success With status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created map to "<name>" using "getPlaceAPI"

    Examples: 
      | name | language | address      |
      | Ravi | English  | Test Address |
      | Raj Kumar | Franch IN | Test address t |
      
  @Deleteplace @Regression
  Scenario: Verify delete functionality is working fine
    Given DeletePlace paylod
    When User call "deletePlaceAPI" with "Post" http request
    Then The API call get success With status code 200
    And "status" in response body is "OK"
