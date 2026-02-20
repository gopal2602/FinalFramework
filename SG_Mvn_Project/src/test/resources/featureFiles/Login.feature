Feature: Login Test Cases are automated

  @LoginTest @LoginSmokeTest @LoginRegressionTest
  Scenario: Verify Login and logout functionality
    Given Verify user launches the "Chrome" browser
    And Verify application URL "AppURL" is loaded successful
    Then Verify user login to application with valid "userName" and "password" credentials
    And Verify user logout from application
