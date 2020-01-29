Feature: Acceptance test with mix of web and api
  Scenario: An acceptance test for BoardGameGeek service

    Given I open homepage of the site
    When I navigate to the page of the game with highest rank in The Hotness left side menu
    And I retrieve information about a particular game from site api
    And I parse response to get most voted option in "Language Dependence" poll
    Then I assert Language Dependence text presented in the page Description block equals the most voted Language Dependence level
    And I close browser
