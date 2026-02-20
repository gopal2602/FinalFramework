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
     * Method Name  : createResultFile()
     * Purpose      : create html file for Extent Report
     ***********************************/
    public static ExtentReports createResultFile(){
        String resultPath = null;
        File objResultPath = null;
        ExtentReports extent = null;
        File objScreenshotPath = null;
        try{
            resultPath = ".\\target\\extent-report";
            objResultPath = new File(resultPath);
            if(!objResultPath.exists()){
                objResultPath.mkdirs();
            }

            objScreenshotPath = new File(resultPath+"\\screenshot");
            if(!objScreenshotPath.exists()){
                objScreenshotPath.mkdirs();
            }
            extent = new ExtentReports(resultPath+"\\TestResults.html", false);
            extent.addSystemInfo("UserName", System.getProperty("user.name"));
            extent.addSystemInfo("HostName", System.getProperty("os.name"));
            extent.addSystemInfo("Environment", propData.get("environment"));
            extent.addSystemInfo("Application Under Test", propData.get("appName"));
            extent.loadConfig(new File(".\\extent-config.xml"));
            return extent;
        }catch(Exception e){
            System.out.println("Exception in 'createResultFile()' method. "+e);
            return null;
        }finally{
            resultPath = null;
            objResultPath = null;
            objScreenshotPath = null;
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
        String screenshotPath = null;
        File srcFile = null;
        File destFile = null;
        TakesScreenshot ts = null;
        try{
            screenshotPath = ".\\target\\extent-report\\screenshot\\screenShot_"+ AppIndependentMethods.getDateTime("hhmmssS")+".png";
            ts = (TakesScreenshot) oBrowser;
            srcFile = ts.getScreenshotAs(OutputType.FILE);
            destFile = new File(screenshotPath);
            FileHandler.copy(srcFile, destFile);
            return screenshotPath;
        }catch(Exception e){
            System.out.println("Exception in 'captureScreenshot()' method. "+e);
            return null;
        }finally{
            screenshotPath = null;
            srcFile = null;
            destFile = null;
            ts = null;
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
