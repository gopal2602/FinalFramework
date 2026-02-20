package sg.org.pages;

import org.openqa.selenium.By;

public class LoginPage {
    public static By obj_LoginLogo_Image = By.xpath("//img[contains(@src, 'timer.png')]");
    public static By obj_UserName_Edit = By.xpath("//input[@id='username']");
    public static By obj_Password_Edit = By.xpath("//input[@name='pwd']");
    public static By obj_Login_Button = By.xpath("//a[@id='loginButton']");
    public static By obj_Hompage_PageText = By.xpath("//td[@class='pagetitle']");
    public static By obj_LoginPage_Header = By.id("headerContainer");
    public static By obj_Logout_Link = By.xpath("//a[@id='logoutLink']");
}
