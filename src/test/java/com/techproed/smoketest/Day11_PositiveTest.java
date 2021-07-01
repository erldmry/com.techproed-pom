package com.techproed.smoketest;

import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.pages.Day11_MainPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Day11_PositiveTest {

    Day11_MainPage mainPage;
    Day11_LoginPage loginPage;
    Day11_DefaultPage defaultPage;
    @BeforeMethod
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
    }

    @Test
    public void positiveTest() {
//        Go to the login page
        mainPage = new Day11_MainPage();
        mainPage.mainPageLoginLink.click();
//        Send username password
        loginPage = new Day11_LoginPage();
//        Send username
        loginPage.username.sendKeys(ConfigReader.getProperty("admin_username"));
//        Send password
        loginPage.password.sendKeys(ConfigReader.getProperty("admin_password"));
//        click on login button
        loginPage.loginButton.click();

//        find a core object in the default login page And verify if the log in successful
        defaultPage = new Day11_DefaultPage();
        Assert.assertTrue(defaultPage.addUserButton.isDisplayed());
    }
}