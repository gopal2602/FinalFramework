package sg.org.baseclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sg.org.common.AppIndependentMethods;
import sg.org.driver.CucumberTestRunner;
import sg.org.pages.UserPage;
import sg.org.reports.ReportUtils;

import java.util.Map;

public class Users2BaseClass extends CucumberTestRunner {
    /***********************************
     * Method Name  : createUser()
     * Purpose      : To create a new user
     ***********************************/
    public static String createUser(WebDriver oBrowser, String FN, String LN, String email, String UN, String PWD, String retypePWD){
        String userName = null;
        try {
            Assert.assertTrue(navigateToAddUserPage(oBrowser));
            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, UserPage.obj_User_FirstName_Edit, FN));
            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, UserPage.obj_User_LastName_Edit, LN));
            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, UserPage.obj_User_Email_Edit, email));
            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, UserPage.obj_User_UserName_Edit, UN));
            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, UserPage.obj_User_Password_Edit, PWD));
            Assert.assertTrue(AppIndependentMethods.setObject(oBrowser, UserPage.obj_User_RetypePassword_Edit, retypePWD));
            userName = LN+", "+FN;
            Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, UserPage.obj_CreateUser_Button));
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyElementPresent(oBrowser, By.xpath(String.format(UserPage.obj_UserName_Link, userName)))){
                ReportUtils.writeResult(oBrowser, "screenshot", "The New user is created successful");
                return userName;
            }else{
                ReportUtils.writeResult(oBrowser, "Fail","Failed to create the new user");
                return null;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'createUser()' method. "+e);
            return null;
        }
    }


    /***********************************
     * Method Name  : navigateToAddUserPage()
     * Purpose      : To navigate to Add User page
     ***********************************/
    public static boolean navigateToAddUserPage(WebDriver oBrowser){
        String strText = null;
        try{
            Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, UserPage.obj_USERS_Menu));
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyText(oBrowser, UserPage.obj_UserPage_Header, "Text", "User List")){
                ReportUtils.writeResult(oBrowser, "Screenshot","'User List' page has opened successful");
            }else {
                ReportUtils.writeResult(oBrowser, "Fail","Failed to open the 'User List' page");
                return false;
            }

            Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, UserPage.obj_AddUser_Button));
            Thread.sleep(2000);

            if(AppIndependentMethods.verifyText(oBrowser, UserPage.obj_AddUser_Header, "Text", "Add User")){
                ReportUtils.writeResult(oBrowser, "Screenshot","'Add User' page has opened successful");
                return true;
            } else {
                ReportUtils.writeResult(oBrowser, "Fail","Failed to open the 'Add User' page");
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'navigateToAddUserPage()' method. "+e);
            return false;
        }
    }


    /***********************************
     * Method Name  : deleteUser()
     * Purpose      : To delete the given user
     ***********************************/
    public static boolean deleteUser(WebDriver oBrowser, String userName){
        try{
            Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, By.xpath(String.format(UserPage.obj_UserName_Link, userName))));
            Thread.sleep(2000);
            Assert.assertTrue(AppIndependentMethods.clickObject(oBrowser, UserPage.obj_DeleteUser_Button));
            Thread.sleep(1000);
            oBrowser.switchTo().alert().accept();
            Thread.sleep(1000);

            if(AppIndependentMethods.verifyElementNotPresent(oBrowser, By.xpath(String.format(UserPage.obj_UserName_Link, userName)))){
                ReportUtils.writeResult(oBrowser, "Screenshot","User was deleted successful");
                return true;
            }else{
                ReportUtils.writeResult(oBrowser, "Fail","Failed to delete the user");
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'deleteUser()' method. "+e);
            return false;
        }
    }
}
