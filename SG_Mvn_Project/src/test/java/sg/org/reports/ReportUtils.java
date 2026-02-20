package sg.org.reports;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import sg.org.common.AppIndependentMethods;
import sg.org.driver.CucumberTestRunner;
import java.io.File;

public class ReportUtils extends CucumberTestRunner {

    /***********************************
     * Method Name  : startExtentReport()
     * Purpose      : create html file for Extent Report
     ***********************************/
    public static ExtentReports startExtentReport(String reportFileName){
        String resultPath = null;
        File objResultPath = null;
        File objScreenshot = null;
        try{
            resultPath = System.getProperty("user.dir") + "\\target\\extent-report";

            objResultPath = new File(resultPath);
            if(!objResultPath.exists()){
                objResultPath.mkdirs();
            }

            screenshotLocation = resultPath + "\\screenshot";
            objScreenshot = new File(screenshotLocation);
            if(!objScreenshot.exists()){
                objScreenshot.mkdirs();
            }

            extent = new ExtentReports(resultPath +"\\"+ reportFileName + ".html", false);
            extent.addSystemInfo("Host Name", System.getProperty("os.name"));
            extent.addSystemInfo("User Name", System.getProperty("user.name"));
            extent.addSystemInfo("Environment", propData.get("environment"));
            extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
            return extent;
        }catch(Exception e){
            System.out.println("Exception in 'startExtentReport()' method. " + e);
            return null;
        }
    }


    /***********************************
     * Method Name  : endExtentReport()
     * Purpose      : after flush, write extent report to .html file
     ***********************************/
    public static void endExtentReport(ExtentTest test){
        try{
            extent.endTest(test);
            extent.flush();
        }catch(Exception e){
            System.out.println("Exception in 'endExtentReport()' method. "+e);
        }
    }


    /***********************************
     * Method Name  : captureScreenshot()
     * Purpose      : after flush, write extent report to .html file
     ***********************************/
    public static String captureScreenshot(WebDriver oBrowser){
        File objSrc = null;
        File objDesc = null;
        String fileLocation = null;
        try{
            fileLocation = screenshotLocation + "\\screenshot_" + AppIndependentMethods.getDateTime("hhmmsS")+".png";
            objSrc = ((TakesScreenshot) oBrowser).getScreenshotAs(OutputType.FILE);
            objDesc = new File(fileLocation);
            FileHandler.copy(objSrc, objDesc);
            return fileLocation;
        }catch(Exception e){
            System.out.println("Exception in 'captureScreenshot()' method. " + e);
            return null;
        }
    }

    /***********************************
     * Method Name  : writeResult()
     * Purpose      : to write the result to extent html file
     ***********************************/
    public static void writeResult(WebDriver oBrowser, String status, String strDescription){
        try{
            switch(status.toLowerCase()){
                case "pass":
                    test.log(LogStatus.PASS, strDescription);
                    break;
                case "fail":
                    test.log(LogStatus.FAIL, strDescription
                            +" : "+test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                case "warning":
                    test.log(LogStatus.WARNING, strDescription);
                    break;
                case "info":
                    test.log(LogStatus.INFO, strDescription);
                    break;
                case "exception":
                    test.log(LogStatus.FATAL, strDescription
                            +" : "+test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                case "screenshot":
                    test.log(LogStatus.PASS, strDescription
                            +" : "+test.addScreenCapture(captureScreenshot(oBrowser)));
                    break;
                default:
                    System.out.println("Invalid result status '"+status+"' was provided");
            }
        }catch(Exception e){
            System.out.println("Exception in 'captureScreenshot()' method. "+e);
        }
    }
    
}
