package sg.org.pages;

import org.openqa.selenium.By;

public class UserPage {
    public static By obj_Shortcut_Dialog = By.xpath("//div[@style='display: block;']");
    public static By getObj_Shortcut_Close_Button = By.id("gettingStartedShortcutsMenuCloseId");
    public static By obj_User_FirstName_Edit = By.xpath("//input[@name='firstName']");
    public static By obj_User_LastName_Edit = By.xpath("//input[@name='lastName']");
    public static By obj_User_Email_Edit = By.xpath("//input[@name='email']");
    public static By obj_User_UserName_Edit = By.xpath("//input[@name='username']");
    public static By obj_User_Password_Edit = By.xpath("//input[@name='password']");
    public static By obj_User_RetypePassword_Edit = By.xpath("//input[@name='passwordCopy']");
    public static By obj_CreateUser_Button = By.xpath("//span[text()='Create User']");
    public static String obj_UserName_Link = "//div[@class='name']/span[text()='%s']";
    public static By obj_USERS_Menu = By.xpath("//div[text()='USERS']");
    public static By obj_UserPage_Header = By.xpath("//div[@class='pagetitle']/span");
    public static By obj_AddUser_Button = By.xpath("//div[text()='Add User']");
    public static By obj_AddUser_Header = By.id("userDataLightBox_titlePlaceholder");
    public static By obj_DeleteUser_Button = By.xpath("//button[contains(text(), 'Delete User')]");
}
