package sg.org.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import sg.org.baseclass.LoginBaseClass;
import sg.org.common.AppDependentMethods;
import sg.org.driver.CucumberTestRunner;

public class Login2StepDefinition extends CucumberTestRunner {
    @And("Verify application URL {string} loaded successful")
    public void verifyApplicationURLLoadedSuccessful(String appURL) {
        Assert.assertTrue(AppDependentMethods.navigateURL(oBrowser, appURL));
    }

    @Then("Verify user login to application using valid {string} and {string} credentials")
    public void verifyUserLoginToApplicationUsingValidAndCredentials(String userName, String password) {
        Assert.assertTrue(LoginBaseClass.loginToApplication(oBrowser, userName, password));
    }
}
