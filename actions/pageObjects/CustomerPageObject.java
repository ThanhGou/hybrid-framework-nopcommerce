package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerPageUI;

public class CustomerPageObject extends BasePage {
    WebDriver driver;
    public CustomerPageObject(WebDriver driver){
        this.driver = driver;
    }
    public String getFirstNameAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.FIRSTNAME_TEXTBOX);
        return getWebElementAttribute(driver, CustomerPageUI.FIRSTNAME_TEXTBOX,  "value");
    }

    public String getLastNameAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.LASTNAME_TEXTBOX);
        return getWebElementAttribute(driver, CustomerPageUI.LASTNAME_TEXTBOX,  "value");
    }

    public String getEmailAttributeValue() {
        waitForElementVisible(driver, CustomerPageUI.EMAIL_TEXTBOX);
        return getWebElementAttribute(driver, CustomerPageUI.EMAIL_TEXTBOX,  "value");
    }
}
