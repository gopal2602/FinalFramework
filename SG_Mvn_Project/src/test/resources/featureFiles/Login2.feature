Feature: Login Test Cases are automated using Example

  @LoginTest123 @LoginSmokeTest @LoginRegressionTest @Regression
 Scenario Outline: Verify Login and logout functionality
    Given Verify user launches the "<browserName>" browser
    And Verify application URL "<appURL>" loaded successful
    Then Verify user login to application using valid "<userName>" and "<password>" credentials
    And Verify user logout from application

    Examples:
    |browserName|appURL                   |userName|password|
    |Chrome     |http://localhost/login.do|admin   |manager |
    |Edge       |http://localhost/login.do|admin   |manager |
    |FireFox    |http://localhost/login.do|admin   |manager |
