package com.nopcommerce.account;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Level_03_BasePage_01_PageObject{
    private WebDriver driver;
    private String projectPath = System.getProperty("user.dir");
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private String emailAddress = getRandomEmail();
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.gecko.driver",projectPath + "/browserDrivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePageObject(driver);
    }
    @Test
    public void User_01_Register_Empty_Data(){
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getFirtNameErrorMessageText(),"First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(),"Last name is required");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Email is required");
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"Password is required");
    }
    @Test
    public void User_02_Register_Invalid_Email(){
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextBox("abc");
        registerPage.enterToLastNameTextBox("xyz");
        registerPage.enterToEmailTextBox("bla@jj@hh");
        registerPage.enterToPasswordTextBox("123456");
        registerPage.enterToConfirmPasswordTextBox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Please enter a valid email address.");
    }
    @Test
    public void User_03_Register_Invalid_Password(){
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextBox("abc");
        registerPage.enterToLastNameTextBox("xyz");
        registerPage.enterToEmailTextBox("a@gmail.com");
        registerPage.enterToPasswordTextBox("123");
        registerPage.enterToConfirmPasswordTextBox("123");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }
    @Test
    public void User_04_Register_Invalid_Confirm_Password(){
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextBox("abc");
        registerPage.enterToLastNameTextBox("xyz");
        registerPage.enterToEmailTextBox("a@gmail.com");
        registerPage.enterToPasswordTextBox("123");
        registerPage.enterToConfirmPasswordTextBox("456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"The password and confirmation password do not match.");
    }
    @Test
    public void User_05_Register_Valid_Data(){
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);
        registerPage.enterToFirstNameTextBox("abc");
        registerPage.enterToLastNameTextBox("xyz");
        registerPage.enterToEmailTextBox(emailAddress);
        registerPage.enterToPasswordTextBox("123456");
        registerPage.enterToConfirmPasswordTextBox("123456");
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
    }
    public void User_06_Login_Success(){
        registerPage.clickToNopCommerceLogo();
        homePage = new HomePageObject(driver);
        homePage.clickToLoginLink();
        loginPage = new LoginPageObject(driver);
        loginPage.enterToEmailTextBox(getRandomEmail());
        loginPage.enterToPasswordTextBox("123456");
        loginPage.clickToLogInButton();
        homePage = new HomePageObject(driver);
        homePage.clickToMyAccountLink();
        customerPage = new CustomerPageObject(driver);
        Assert.assertEquals(customerPage.getFirstNameAttributeValue(),"abc");
        Assert.assertEquals(customerPage.getLastNameAttributeValue(),"xyz");
        Assert.assertEquals(customerPage.getEmailAttributeValue(), emailAddress);
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
