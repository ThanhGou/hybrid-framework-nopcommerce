package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
    WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }
    public RegisterPageObject clickToRegisterLink(){
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }

    public LoginPageObject clickToLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickToElement(driver, HomePageUI.LOGIN_LINK);
        return PageGeneratorManager.getLoginPage(driver);
    }

    public CustomerPageObject clickToMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.ACCOUNT_LINK);
        clickToElement(driver, HomePageUI.ACCOUNT_LINK);
        return PageGeneratorManager.getCustomerPage(driver);
    }
}
