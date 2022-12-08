@UI
Feature: This feature is used for google.com related tests

  @All @Regression @GoogleSearch
  Scenario:As a google.com visitor i want be able to search for Wikipedia website

    Given User is on 'https:\\google.com' page
    When User enter 'Wikipedia' in search box
    And User click search button
    Then User can see multiple search results

