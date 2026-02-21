Feature: Users Test Cases are automated

  @UserTest123 @UserSmokeTest @UserRegressionTest
  Scenario Outline: Verify create and delete user functionality
    Given Verify user launches the "<browserName>" browser
    And Verify application URL "<appURL>" loaded successful
    Then Verify user login to application using valid "<userName>" and "<password>" credentials
    Then Verify create user functionality with "<firstName>", "<lastName>", "<email>", "<userUSerName>", "<userPassword>" and "<retypePassword>" inputs
    And Verify delete user functionality
    And Verify user logout from application

    Examples:
    |browserName|appURL                   |userName|password |firstName|lastName|email           |userUSerName|userPassword|retypePassword|
    |Chrome     |http://localhost/login.do|admin   |manager  |demo     |test1   |demotest1@sg.com|demotest1   |Mercury1    |Mercury1      |
    |Edge       |http://localhost/login.do|admin   |manager  |test     |test1   |testtest1@sg.com|testtest1   |Mercury1    |Mercury1      |