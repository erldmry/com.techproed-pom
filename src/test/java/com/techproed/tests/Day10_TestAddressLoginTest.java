package com.techproed.tests;

import com.techproed.pages.Day10_TestAddressLoginPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.annotations.Test;

public class Day10_TestAddressLoginTest {


    //    Creating page class
    Day10_TestAddressLoginPage taPage = new Day10_TestAddressLoginPage();

    @Test
    public void testAddressLoginTest() {
//        TRADITIONAL AUTOMATION CLASS
//        Driver.getDriver().get("http://a.testaddressbook.com/sign_in");
//        Driver.getDriver().findElement(By.id("session_email")).sendKeys("testtechproed@gmail.com");
//        Driver.getDriver().findElement(By.id("session_password")).sendKeys("Test1234!");
//        Driver.getDriver().findElement(By.xpath("//input[@type='submit']")).click();

//        POM AUTOMATION CLASS
//        Driver.getDriver().get(ConfigReader.getProperty("test_address_url"));
        Driver.getDriver().get(ConfigReader.getProperty("test_address_url"));
        taPage.email.sendKeys(ConfigReader.getProperty("test_address_username"));
        taPage.password.sendKeys(ConfigReader.getProperty("test_address_password"));
        taPage.singInButton.click();

    }
}