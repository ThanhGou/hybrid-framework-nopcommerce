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

public class Level_02_BasePage_02_Init {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
//Khi can su dung 1 class, can phai khai bao va khoi tao no len
    private BasePage basePage;
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        basePage = new BasePage();
        driver.manage().window().maximize();
    }
    @Test
    public void Register_01_Empty_Data(){
        basePage.openPageURL(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class ='ico-register']");
        basePage.clickToElement(driver, "//button[@id='register-button']");

        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='FirstName-error']"),"First name is required.");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='LastName-error']"),"Last name is required.");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='Email-error']"),"Email is required.");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id='Password-error']"),"Password is required.");
    }
    @Test
    public void Register_02_Invalid_Email(){
        basePage.openPageURL(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class ='ico-register']");
        basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        basePage.sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        basePage.sendKeyToElement(driver, "//input[@id='Email']", "bla@jj@hh");
        basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        basePage.clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id = 'Email-error']"),"Please enter a valid email address.");
    }
    @Test
    public void Register_03_Invalid_Password(){
        basePage.openPageURL(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class ='ico-register']");
        basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        basePage.sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        basePage.sendKeyToElement(driver, "//input[@id='Email']", "a@gmail.com");
        basePage.sendKeyToElement(driver, "//input[@id='Password']", "123");
        basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
        basePage.clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@class = 'field-validation-error']"),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }
    @Test
    public void Register_04_Invalid_Confirm_Password(){
        basePage.openPageURL(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class ='ico-register']");
        basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        basePage.sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        basePage.sendKeyToElement(driver, "//input[@id='Email']", "a@gmail.com");
        basePage.sendKeyToElement(driver, "//input[@id='Password']", "123");
        basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "456");
        basePage.clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(basePage.getWebElementText(driver, "//span[@id = 'ConfirmPassword-error']"),"The password and confirmation password do not match.");
    }
    @Test
    public void Register_05_Valid_Data(){
        basePage.openPageURL(driver, "https://demo.nopcommerce.com/");
        basePage.clickToElement(driver, "//a[@class ='ico-register']");
        basePage.sendKeyToElement(driver, "//input[@id='FirstName']", "abc");
        basePage.sendKeyToElement(driver, "//input[@id='LastName']", "xyz");
        basePage.sendKeyToElement(driver, "//input[@id='Email']", getRandomEmail());
        basePage.sendKeyToElement(driver, "//input[@id='Password']", "123456");
        basePage.sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
        basePage.clickToElement(driver, "//button[@id = 'register-button']");
        Assert.assertEquals(basePage.getWebElementText(driver, "//div[@class = 'result']"),"Your registration completed");
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
