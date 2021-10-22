Feature: Test Post a film to our API and then get the stored film
  Description: use this test to make sure api works fine

  Scenario: Add film and get it
    Given add a new film
    When get the film by id
    Then it must be the one that we add before