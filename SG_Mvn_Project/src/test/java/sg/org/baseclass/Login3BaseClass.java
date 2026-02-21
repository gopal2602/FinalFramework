package sg.org.baseclass;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sg.org.common.AppIndependentMethods;
import sg.org.driver.CucumberTestRunner;
import sg.org.pages.LoginPage;
import sg.org.pages.UserPage;
import sg.org.reports.ReportUtils;

import java.util.List;
import java.util.Map;

public class Login3BaseClass extends CucumberTestRunner {
    /***********************************
     * Method Name  : loginToApplication()
     * Purpose      : login to actiTime application
     ***********************************/
    public static boolean loginToApplication(WebDriver oBrowser, DataTable dataTable){
        String strText = null;
        List<Map<String, String>> inputData = null;
        try{
            inputData = dataTable.asMaps(String.class, String.class);
            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, LoginPage.obj_UserName_Edit, inputData.get(0).get("userName")));

            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, LoginPage.obj_Password_Edit, inputData.get(0).get("Password")));
            Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, LoginPage.obj_Login_Button));
            Thread.sleep(2000);
            boolean blnRes = AppIndependentMethods.verifyText(oBrowser, LoginPage.obj_Hompage_PageText, "Text", "Enter Time-Track");
            if(blnRes){
                ReportUtils.writeResult(oBrowser, "Screenshot", "Login to actiTime successful");
                if(AppIndependentMethods.verifyOptionalElement(oBrowser, UserPage.obj_Shortcut_Dialog)){
                    Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, UserPage.getObj_Shortcut_Close_Button));
                }
                return true;
            }else{
                ReportUtils.writeResult(oBrowser, "Fail","Failed to login to actiTime");
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'loginToApplication()' method. "+e);
            return false;
        }
    }


    /***********************************
     * Method Name  : logoutFromApplication()
     * Purpose      : logout from actiTime application
     ***********************************/
    public static boolean logoutFromApplication(WebDriver oBrowser){
        try{
            Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, LoginPage.obj_Logout_Link));
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyText(oBrowser, LoginPage.obj_LoginPage_Header, "Text", "Please identify yourself")){
                ReportUtils.writeResult(oBrowser, "Screenshot","Logout form the actiTime was successful");
                return true;
            }else{
                ReportUtils.writeResult(oBrowser, "Fail","Failed to logout from the actiTime");
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'logoutFromApplication()' method. "+e);
            return false;
        }
    }
}
