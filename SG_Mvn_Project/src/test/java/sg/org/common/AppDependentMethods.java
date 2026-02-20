package sg.org.common;

import org.openqa.selenium.WebDriver;
import sg.org.driver.CucumberTestRunner;
import sg.org.pages.LoginPage;
import sg.org.reports.ReportUtils;

public class AppDependentMethods extends CucumberTestRunner {
    /***********************************
     * Method Name  : navigateURL()
     * Purpose      : navigates the URL
     ***********************************/
    public static boolean navigateURL(WebDriver oBrowser, String strURL){
        try{
            oBrowser.navigate().to(strURL);
            Thread.sleep(2000);
            boolean blnRes = AppIndependentMethods.verifyElementPresent(oBrowser, LoginPage.obj_LoginLogo_Image);
            if(blnRes) {
                ReportUtils.writeResult(oBrowser, "Screenshot", "URL is loaded successful");
                return true;
            } else {
                ReportUtils.writeResult(oBrowser, "Fail","Failed to load the URL");
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'navigateURL()' method. "+e);
            return false;
        }
    }
}
