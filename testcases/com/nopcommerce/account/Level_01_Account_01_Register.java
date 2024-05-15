package com.nopcommerce.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Level_01_Account_01_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }
    @Test
    public void Register_01_Empty_Data(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(),"First name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(),"Last name is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(),"Email is required.");
        Assert.assertEquals(driver.findElement(By.cssSelector("ConfirmPassword-error")).getText(),"Password is required.");
    }
    @Test
    public void Register_02_Invalid_Email(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("abc");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("xyz");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("bla@jj@hh");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(),"Please enter a valid email address.");
    }
    @Test
    public void Register_03_Invalid_Password(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("abc");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("xyz");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("a@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.field-validation-error")).getText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }
    @Test
    public void Register_04_Invalid_Confirm_Password(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("abc");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("xyz");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("a@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(),"The password and confirmation password do not match.");
    }
    @Test
    public void Register_05_Valid_Data(){
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("abc");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("xyz");
        driver.findElement(By.cssSelector("input#Email")).sendKeys(getRandomEmail());
        driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#register-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
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
