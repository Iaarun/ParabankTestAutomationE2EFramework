Feature: Fund Transfer

  @transfer @regression
  Scenario: Transfer funds successfully between accounts
    Given user logs into application
    When user transfers "200" amount
    Then transfer should be successful