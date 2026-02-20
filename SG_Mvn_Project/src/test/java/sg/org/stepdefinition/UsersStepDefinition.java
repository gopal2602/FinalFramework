package sg.org.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import sg.org.baseclass.UsersBaseClass;
import sg.org.driver.CucumberTestRunner;
import java.util.HashMap;
import java.util.Map;

public class UsersStepDefinition extends CucumberTestRunner {
    @Then("Verify create new user functionality with {string}, {string}, {string}, {string}, {string} and {string} inputs")
    public void verifyCreateNewUserFunctionality(String firstName, String lastName, String email, String userName, String password, String retypePassword) {
        Map<String, String> userData = new HashMap<String, String>();
        userData.put("user_FirstName", firstName);
        userData.put("user_LastName", lastName);
        userData.put("user_Email", email);
        userData.put("user_UserName", userName);
        userData.put("user_Password", password);
        userData.put("user_RetypePassword", retypePassword);
        userCreated = UsersBaseClass.createUser(oBrowser, userData);
        Assert.assertNotNull(userData, "Failed to get the newly created user name");
    }

    @And("Verify delete user functionality")
    public void verifyDeleteUserFunctionality() {
        Assert.assertTrue(UsersBaseClass.deleteUser(oBrowser, userCreated));
    }
}
