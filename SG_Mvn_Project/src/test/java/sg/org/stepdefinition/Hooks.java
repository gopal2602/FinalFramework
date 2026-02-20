package sg.org.stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import sg.org.driver.CucumberTestRunner;
import sg.org.reports.ReportUtils;

public class Hooks extends CucumberTestRunner {

    @Before
    public void setUp(Scenario scenario){
        String scenarioName = null;
        try{
            scenarioName = scenario.getName();
            test = extent.startTest(scenarioName);
        }catch(Exception e){
            System.out.println("Exception in 'setUp()' method. "+ e);
        }
    }


    @After
    public void tearDown(Scenario scenario){
        try{
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) oBrowser).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }
            if(oBrowser!=null){
                oBrowser.close();
                oBrowser = null;
            }
            ReportUtils.endExtentReport(test);
        }catch(Exception e){
            System.out.println("Exception in 'tearDown()' method. "+ e);
        }
    }
}
