Feature: Login Functionality

  @login @regression
  Scenario: Successful login with valid credentials
    When the user enters username "john" and password "demo"
    And  user clicks on the login button
    Then the user should be logged in successfully
