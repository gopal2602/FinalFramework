Feature: Users Test Cases are automated

  @UserTest @UserSmokeTest @UserRegressionTest @Regression
  Scenario: Verify create and delete user functionality
    Given Verify user launches the "Chrome" browser
    And Verify application URL "AppURL" is loaded successful
    Then Verify user login to application with valid "userName" and "password" credentials
    Then Verify create new user functionality with "sg", "user1", "sg.user1@sg.com", "sguser1", "Mercury@1" and "Mercury@1" inputs
    And Verify delete user functionality
    And Verify user logout from application