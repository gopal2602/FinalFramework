package sg.org.stepdefinition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import sg.org.baseclass.LoginBaseClass;
import sg.org.common.AppDependentMethods;
import sg.org.common.AppIndependentMethods;
import sg.org.driver.CucumberTestRunner;

public class LoginStepDefinition extends CucumberTestRunner {
    @Given("Verify user launches the {string} browser")
    public void verifyUserLaunchesTheBrowser(String browserName) {
        oBrowser = AppIndependentMethods.launchBrowser(browserName);
        Assert.assertNotNull(oBrowser, "Browser object is null");
    }

    @Then("Verify user login to application with valid {string} and {string} credentials")
    public void verifyUserLoginToApplicationWithValidAndCredentials(String userName, String password) {
        Assert.assertTrue(LoginBaseClass.loginToApplication(oBrowser, propData.get(userName), propData.get(password)));
    }

    @And("Verify user logout from application")
    public void verifyUserLogoutFromApplication() {
       Assert.assertTrue(LoginBaseClass.logoutFromApplication(oBrowser));
    }

    @And("Verify application URL {string} is loaded successful")
    public void verifyApplicationURLIsLoadedSuccessful(String appUrl) {
        Assert.assertTrue(AppDependentMethods.navigateURL(oBrowser, propData.get(appUrl)));
    }
}
