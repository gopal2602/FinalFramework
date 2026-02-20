package sg.org.driver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import sg.org.common.AppIndependentMethods;
import sg.org.reports.ReportUtils;
import java.util.Map;

@CucumberOptions(
        glue = {"sg.org.stepdefinition"},
        plugin = {
                "pretty",
                "html:target/cucumber-report/cucumberReport.html",
                "json:target/cucumber-report/cucumber.json"
        },
        features = {"src/test/resources/featureFiles"},
        dryRun = false, monochrome = true
)

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
    public static ExtentReports extent = null;
    public static Map<String, String> propData = null;
    public static ExtentTest test = null;
    public static WebDriver oBrowser = null;
    public static String userCreated = null;
    public static String screenshotLocation = null;

    @BeforeSuite
    public void preRequisites(){
        String propFilePath = null;
        try{
            propFilePath = ".\\src\\main\\resources\\QA.properties";
            propData = AppIndependentMethods.getPropData(propFilePath);
            extent = ReportUtils.startExtentReport("AutomationTestReport");
        }catch(Exception e){
            System.out.println("Exception in 'preRequisites()' method. "+e);
        }
    }
}
