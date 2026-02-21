package sg.org.stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import sg.org.baseclass.Login3BaseClass;
import sg.org.common.AppDependentMethods;
import sg.org.common.AppIndependentMethods;
import sg.org.driver.CucumberTestRunner;
import sg.org.reports.ReportUtils;
import java.util.List;
import java.util.Map;

public class Login3StepDefinition extends CucumberTestRunner {
    @Given("Verify user launches the browser")
    public void verifyUserLaunchesTheBrowser(DataTable dataTable) {
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            oBrowser = AppIndependentMethods.launchBrowser(inputData.get(0).get("browserName"));
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception", "Exception in 'verifyUserLaunchesTheBrowser()' method."+ e);
        }
    }

    @And("Verify application URL loaded successful")
    public void verifyApplicationURLLoadedSuccessful(DataTable dataTable) {
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            Assert.assertTrue(AppDependentMethods.navigateURL(oBrowser, inputData.get(0).get("appURL")));
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception", "Exception in 'verifyApplicationURLLoadedSuccessful()' method."+ e);
        }
    }

    @Then("Verify user login to application using valid credentials")
    public void verifyUserLoginToApplicationUsingValidCredentials(DataTable dataTable) {
        Assert.assertTrue(Login3BaseClass.loginToApplication(oBrowser, dataTable));
    }
}
