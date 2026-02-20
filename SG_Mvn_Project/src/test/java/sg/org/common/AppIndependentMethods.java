package sg.org.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import sg.org.driver.CucumberTestRunner;
import sg.org.reports.ReportUtils;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class AppIndependentMethods extends CucumberTestRunner {
    /***********************************
     * Method Name  : getDateTime()
     * Purpose      : gives today's date as TimeStamp
     ***********************************/
    public static String getDateTime(String dateFormat){
        Date dt = null;
        SimpleDateFormat sdf = null;
        try{
            dt = new Date();
            sdf = new SimpleDateFormat(dateFormat);
            return sdf.format(dt);
        }catch(Exception e){
            System.out.println("Exception in 'getDateTime()' method. "+e);
            return null;
        }finally{
            dt = null;
            sdf = null;
        }
    }


    /***********************************
     * Method Name  : getPropData()
     * Purpose      : reads the prop file values in the form of Map<String, String>
     ***********************************/
    public static Map<String, String> getPropData(String strPropFilePath){
        FileInputStream fin = null;
        Properties prop = null;
        Map<String, String> objData = null;
        try{
            objData = new HashMap<>();
            fin = new FileInputStream(strPropFilePath);
            prop = new Properties();
            prop.load(fin);

            Set<Map.Entry<Object, Object>> both = prop.entrySet();
            Iterator<Map.Entry<Object, Object>> it = both.iterator();
            while(it.hasNext() == true){
                Map.Entry<Object, Object> mp = it.next();
                objData.put(mp.getKey().toString(), mp.getValue().toString());
            }
            return objData;
        }catch(Exception e){
            ReportUtils.writeResult(null, "Exception", "Exception in 'getPropData()' method. " + e);
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                prop = null;
            }catch(Exception e){}
        }
    }


    /***********************************
     * Method Name  : clickObject()
     * Purpose      : click on WebElement
     ***********************************/
    public static boolean clickObject(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.click();
                ReportUtils.writeResult(oBrowser, "Pass", "The Element '"+objBy+"' clicked successful");
            }
            return true;
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception", "Exception in 'clickObject()' method. "+e);
            return false;
        }finally{
            oEle = null;
        }
    }


    /***********************************
     * Method Name  : setObject()
     * Purpose      : sets/enter the values in WebElement
     ***********************************/
    public static boolean setObject(WebDriver oBrowser, By objBy, String strData){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.sendKeys(strData);
                ReportUtils.writeResult(oBrowser, "Pass", "The value '"+strData+"' is entered in the Element '"+objBy+"' successful");
            }
            return true;
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'setObject()' method. "+e);
            return false;
        }finally{
            oEle = null;
        }
    }


    /***********************************
     * Method Name  : compareText()
     * Purpose      : to compare the actual & expected values
     ***********************************/
    public static boolean compareText(WebDriver oBrowser, String actual, String expected){
        try{
            if(actual.equalsIgnoreCase(expected)){
                ReportUtils.writeResult(oBrowser, "Pass","The actual '"+actual+"' & expected '"+expected+"' values are matching");
                return true;
            }else{
                ReportUtils.writeResult(oBrowser, "Fail","Mis-match in actual '"+actual+"' & expected '"+expected+"' values");
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'compareText()' method. "+e);
            return false;
        }
    }


    /***********************************
     * Method Name  : verifyText()
     * Purpose      : to verify the text present in the element
     ***********************************/
    public static boolean verifyText(WebDriver oBrowser, By objBy, String objectType, String expected){
        WebElement oEle = null;
        Select oSelect = null;
        String actual = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                switch(objectType.toLowerCase()){
                    case "text":
                        actual = oEle.getText();
                        break;
                    case "value":
                        actual = oEle.getAttribute("value");
                        break;
                    case "dropdown":
                        oSelect = new Select(oEle);
                        actual = oSelect.getFirstSelectedOption().getText();
                        break;
                    default:
                        ReportUtils.writeResult(oBrowser, "Fail", "Invalid object type '"+objectType+"' was specified.");
                        return false;
                }

                if(compareText(oBrowser, actual, expected)) return true;
                else return false;
            }
            return true;
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'verifyText()' method. "+e);
            return false;
        }finally{
            oEle = null;
            oSelect = null;
        }
    }


    /***********************************
     * Method Name  : verifyElementPresent()
     * Purpose      : to validate the present of element in the DOM
     ***********************************/
    public static boolean verifyElementPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                ReportUtils.writeResult(oBrowser, "Pass", "The Element '"+objBy+"' present in the application DOM");
                return true;
            }else{
                ReportUtils.writeResult(oBrowser, "Fail","The Element '"+objBy+"' NOT present in the application DOM");
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'verifyElementPresent()' method. "+e);
            return false;
        }
    }



    /***********************************
     * Method Name  : verifyElementNotPresent()
     * Purpose      : to validate the invisibility of element in the DOM
     ***********************************/
    public static boolean verifyElementNotPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                ReportUtils.writeResult(oBrowser, "Fail","The Element '"+objBy+"' Still present in the application DOM");
                return false;
            }else{
                ReportUtils.writeResult(oBrowser, "Pass","The Element '"+objBy+"' NOT present in the application DOM");
                return true;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'verifyElementNotPresent()' method. "+e);
            return false;
        }
    }


    /***********************************
     * Method Name  : verifyOptionalElement()
     * Purpose      : to validate the optional element in the DOM
     ***********************************/
    public static boolean verifyOptionalElement(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            ReportUtils.writeResult(oBrowser, "Exception","Exception in 'verifyOptionalElement()' method. "+e);
            return false;
        }
    }


    /***********************************
     * Method Name  : launchBrowser()
     * Purpose      : to launch the required browser
     ***********************************/
    public static WebDriver launchBrowser(String browserName){
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                default:
                    ReportUtils.writeResult(oDriver, "Fail", "Invalid browser name '"+browserName+"' was mentioned.");
                    return null;
            }
            if(oDriver!=null){
                oDriver.manage().window().maximize();
                ReportUtils.writeResult(oDriver, "Pass","The '"+browserName+"' browser launched successful");
                return oDriver;
            }else{
                ReportUtils.writeResult(oDriver, "Fail","Failed to launch the '"+browserName+"' browser.");
                return null;
            }
        }catch(Exception e){
            ReportUtils.writeResult(null, "Exception","Exception in 'launchBrowser()' method. "+e);
            return null;
        }
    }

}
