package com.techproed.homework;

import com.techproed.pages.Day11_DefaultPage;
import com.techproed.pages.Day11_LoginPage;
import com.techproed.pages.Day11_MainPage;
import com.techproed.homework.HW_RoomReservationPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HW_HotelRoomReservationTest {
    Day11_MainPage mainPage;
    Day11_LoginPage loginPage;
    Day11_DefaultPage defaultPage;
    HW_RoomReservationPage roomReservationPage;
    //This ll take me to the Room Reservation Page
    @BeforeMethod
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("application_url"));
        mainPage=new Day11_MainPage();
        mainPage.mainPageLoginLink.click();
        loginPage=new Day11_LoginPage();
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();
        defaultPage=new Day11_DefaultPage();
        defaultPage.hotelManagement.click();
        defaultPage.roomReservations.click();
    }
    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }
    @Test
    public void roomReservationTest() throws InterruptedException {
        ReusableMethods.waitForPageToLoad(10);
        roomReservationPage=new HW_RoomReservationPage();
        roomReservationPage.addRoomReservationButton.click();
        Select idUserOptions=new Select(roomReservationPage.idUser);
        idUserOptions.selectByIndex(2);
        Select idHotelOptions=new Select(roomReservationPage.idHotelRoom);
        idHotelOptions.selectByIndex(3);
        roomReservationPage.price.sendKeys("600");
        ReusableMethods.waitFor(1);
        String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        String checkinDate = (Integer.parseInt(today.substring(0,2))+2)+today.substring(2);
        String checkoutDate = ((Integer.parseInt(today.substring(0,2))+5)+today.substring(2));
        System.out.println(today+" : "+checkinDate+" : "+checkoutDate);
//        roomReservationPage.dateStart.sendKeys("01/23/2022");
//        roomReservationPage.dateEnd.sendKeys("01/24/2022");
        roomReservationPage.dateStart.sendKeys(checkinDate);
        ReusableMethods.waitFor(1);
        roomReservationPage.dateEnd.sendKeys(checkoutDate);
        roomReservationPage.adultAmount.sendKeys("2");
        roomReservationPage.childrenAmount.sendKeys("3");
        roomReservationPage.nameAndSurname.sendKeys("James Bond");
//        Thread.sleep(2000);
        roomReservationPage.contactPhone.sendKeys("2222222222");
        roomReservationPage.contactEmail.sendKeys("abc@gmail.com");
        roomReservationPage.notes.sendKeys("Testing");
        roomReservationPage.approved.click();
        roomReservationPage.isPaid.click();
        roomReservationPage.saveButton.click();
//        WHEN WE NEED EXPLICIT WAIT< WE CAN USE THE ONE THAT IS IN TEH REUSABLE METHODS CLASS
        WebElement actualSuccessMessage= ReusableMethods.waitForVisibility(By.className("bootbox-body"),10);
        //USING THE OTHER EXMPLICIT WAIT
        //WebElement actualSuccessMessage= ReusableMethods.waitForVisibility(Driver.getDriver().findElement(By.className("bootbox-body")),10);
        // WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);
        // WebElement actualSuccessMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bootbox-body")));
//        String actualReservationSuccessMessage=roomReservationPage.successMessage.getText();
//        String expectedReservationSuccessMessage="RoomReservation was inserted successfully";
//       boolean isTrue=wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='bootbox-body']"),ConfigReader.getProperty("expectedReservationSuccessMessage")));
//       Assert.assertTrue(isTrue);
        String actualSuccessMessageText=actualSuccessMessage.getText();
        Assert.assertEquals(actualSuccessMessageText,ConfigReader.getProperty("expectedReservationSuccessMessage"));
        // Assert.assertEquals(roomReservationPage.actualSuccessMessage.getText(),ConfigReader.getProperty("expectedReservationSuccessMessage"));
    }

}
