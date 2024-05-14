package com.nopcommerce.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_05_WebDriver_Manager_Selenium_Manager extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private String emailAddress = getRandomEmail();
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){
        driver = getBrowserDriver(browserName);

        //mo 1 URL hoac tu page nay chuyen qua page khac -> can khoi tao page do len
        homePage = new HomePageObject(driver);
    }
    @Test
    public void User_01_Register_Empty_Data(){
        homePage.clickToRegisterLink();
        //tu homepage click Register link se vao Register page -> khoi tao object cho register page
        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();
        Assert.assertEquals(registerPage.getFirtNameErrorMessageText(),"First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(),"Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(),"Email is required");
        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"Password is required");
    }
    @Test
    public void User_02_Register_Invalid_Email(){
        //dang o register page sau TC 01, click vao logo -> vao lai homepage
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
        closeBrowser();
    }

}
