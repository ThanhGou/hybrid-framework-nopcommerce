package com.nopcommerce.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_02_BasePage_03_Inheritance extends BasePage{
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @Test
    public void Register_01_Empty_Data(){
        openPageURL(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class ='ico-register']");
        clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(getWebElementText(driver, "//span[@id='FirstName-error']"),"First name is required.");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='LastName-error']"),"Last name is required.");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='Email-error']"),"Email is required.");
        Assert.assertEquals(getWebElementText(driver, "//span[@id='Password-error']"),"Password is required.");
    }
    @Test
    public void Register_02_Invalid_Email(){
        openPageURL(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class ='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        sendKeyToElement(driver, "//input[@id='Email']", "bla@jj@hh");
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(getWebElementText(driver, "//span[@id = 'Email-error']"),"Please enter a valid email address.");
    }
    @Test
    public void Register_03_Invalid_Password(){
        openPageURL(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class ='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        sendKeyToElement(driver, "//input[@id='Email']", "a@gmail.com");
        sendKeyToElement(driver, "//input[@id='Password']", "123");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
        clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(getWebElementText(driver, "//span[@class = 'field-validation-error']"),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }
    @Test
    public void Register_04_Invalid_Confirm_Password(){
        openPageURL(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class ='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        sendKeyToElement(driver, "//input[@id='Email']", "a@gmail.com");
        sendKeyToElement(driver, "//input[@id='Password']", "123");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "456");
        clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(getWebElementText(driver, "//span[@id = 'ConfirmPassword-error']"),"The password and confirmation password do not match.");
    }
    @Test
    public void Register_05_Valid_Data(){
        openPageURL(driver, "https://demo.nopcommerce.com/");
        clickToElement(driver, "//a[@class ='ico-register']");
        sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        sendKeyToElement(driver, "//input[@id='Email']", getRandomEmail());
        sendKeyToElement(driver, "//input[@id='Password']", "123456");
        sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(getWebElementText(driver, "//div[@class = 'result']"),"Your registration completed");
    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    public String getRandomEmail(){
        Random rand = new Random();
        return "test" + rand.nextInt(1000) + "@gmail.com";
    }
}
