package com.techproed.excelautomation;

import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Day14_LoginTestWithExcel {

    //    Creating ExcelUtil object
    ExcelUtil excelUtil;
    //    We will get test data (username,pasword) key value list as LIST OF MAP OF STRING
    List<Map<String , String>> testData;
    Day11_LoginPage loginPage;
    Day11_DefaultPage defaultPage;
    //    To login the application
    public void setUp(){
        loginPage = new Day11_LoginPage();
        Driver.getDriver().get(ConfigReader.getProperty("application_login_url"));
        //We are on the Day11_LoginPage
    }
    @Test
    public void adminLoginTest(){
//   Calling ExcelUtil to set the path and sheet name
        excelUtil=new ExcelUtil("./src/test/java/resources/smoketestdata.xlsx","admin_login_info");
//        Now that we have access to teh Excel Util class, We can use ANY REUSABLE METHOD IN THAT CLASS.
//        FOR EXAMPLE to get the number of column in admin_login_info worksheet:
//        System.out.println(excelUtil.columnCount());//2
//        FOR EXAMPLE to get the data in the 1st ROW 2nd CELL in admin_login_info:
//        System.out.println(excelUtil.getCellData(0,1));//username
//        FOR EXAMPLE to get the list of data in admin_login_info sheet:
//        System.out.println(excelUtil.getDataList());//[{password=Techproed123!, username=admin}]
//        SHORTCUT TO GO TO METHOD : command+click  or control + click
        testData=excelUtil.getDataList();
        for(Map<String,String> each: testData) {
            setUp();
//            System.out.println(each);
//           Map get method accepts a key and returns the value.
            loginPage.username.sendKeys(each.get("username"));//admin
            loginPage.password.sendKeys(each.get("password"));//Techproed123!
            loginPage.loginButton.click();
            defaultPage = new Day11_DefaultPage();
            Assert.assertEquals(defaultPage.userID.getText(), each.get("username"));
        }
    }
    @Test
    public void managerLoginTest(){
//        Manager should be able to log in the application using manager credentials
//        Get the manager credentials from the manager_login_info
//      Calling ExcelUtil to set the path and sheet name
        excelUtil=new ExcelUtil("./src/test/java/resources/smoketestdata.xlsx","manager_login_info");
        testData=excelUtil.getDataList();
        for(Map<String,String> each: testData) {
            setUp();
//            System.out.println(each);
//           Map get method accepts a key and returns the value.
            loginPage.username.sendKeys(each.get("username"));
            loginPage.password.sendKeys(each.get("password"));
            loginPage.loginButton.click();
            defaultPage = new Day11_DefaultPage();
            Assert.assertEquals(defaultPage.userID.getText(), each.get("username"));
        }
    }
}