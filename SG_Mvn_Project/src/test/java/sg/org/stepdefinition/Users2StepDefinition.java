package sg.org.stepdefinition;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import sg.org.baseclass.Users2BaseClass;
import sg.org.driver.CucumberTestRunner;

public class Users2StepDefinition extends CucumberTestRunner {
    @Then("Verify create user functionality with {string}, {string}, {string}, {string}, {string} and {string} inputs")
    public void verifyCreateUserFunctionalityWithAndInputs(String firstName, String lastName, String email, String userName, String password, String retypePwd) {
        userCreated = Users2BaseClass.createUser(oBrowser, firstName, lastName, email, userName, password, retypePwd);
        Assert.assertNotNull(userCreated, "Failed to get the newly created user name");
    }
}
