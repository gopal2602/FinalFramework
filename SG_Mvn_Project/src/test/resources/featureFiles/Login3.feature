Feature: Login Test Cases are automated using Example

   @LoginTest333 @LoginSmokeTest @LoginRegressionTest
   Scenario: Verify Login and logout functionality1
      Given Verify user launches the browser
         | browserName |
         | FireFox     |
      And Verify application URL loaded successful
         | appURL                    |
         | http://localhost/login.do |
      Then Verify user login to application using valid credentials
         | userName | Password |
         | admin    | manager  |
      And Verify user logout from application