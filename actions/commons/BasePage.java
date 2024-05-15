package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    public void openPageURL(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public void getCurrentPageURL(WebDriver driver){
        driver.getCurrentUrl();
    }
    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }
    public Alert waitAlertPresent(WebDriver driver){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }
    public void acceptAlert(WebDriver driver){
        waitAlertPresent(driver).accept();
    }
    public void cancelAlert(WebDriver driver){
        waitAlertPresent(driver).dismiss();
    }
    public void getTextAlert(WebDriver driver){
        waitAlertPresent(driver).getText();
    }
    public void sendKeyAlert(WebDriver driver, String keyToSend){
        waitAlertPresent(driver).sendKeys(keyToSend);
    }
    public void switchToWindowByTitle(WebDriver driver, String pageTitle){
        String currentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle: windowHandles){
            if (!windowHandle.equals(currentWindow)){
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().equals(pageTitle)){
                    break;
                }
            }
        }
    }
    public Set<Cookie> getBrowserCookies(WebDriver driver){
        return driver.manage().getCookies();
    }
    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies){
            driver.manage().addCookie(cookie);
        }
    }
    public void deleteAllCookies(WebDriver driver){
        driver.manage().deleteAllCookies();
    }
    public By getByXpath(String locator){
        return By.xpath(locator);
    }
    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByXpath(locator));
    }
    public List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }
    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver,locator).click();
    }
    public void sendKeyToElement(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver,locator).clear();
        getWebElement(driver,locator).sendKeys(valueToSend);
    }
    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectItemDefaultDropdown(WebDriver driver, String locator, String textItem){
        Select selectItem = new Select(getWebElement(driver, locator));
        selectItem.selectByVisibleText(textItem);
    }
    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator){
        Select selectItem = new Select(getWebElement(driver, locator));
        return selectItem.getFirstSelectedOption().getText();
    }
    public boolean isDefaultDropdownMultiple(WebDriver driver, String locator){
        Select selectItem = new Select(getWebElement(driver, locator));
        return selectItem.isMultiple();
    }
    public void selectItemInDropdown(WebDriver driver ,String parentLocator, String childItemLocator, String expectedItemText){
        getWebElement(driver, parentLocator).click();
        sleepInSecond(3);
        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
        for (WebElement item: allItems){
            String textItem = item.getText();
            if (textItem.equals(expectedItemText)){
                item.click();
                break;
            }
        }
    }
    public String getWebElementText(WebDriver driver, String locator){
        return getWebElement(driver,locator).getText();
    }
    public String getWebElementAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getAttribute(attributeName);
    }
    public String getWebElementCssValue(WebDriver driver, String locator, String cssPropertyName){
        return getWebElement(driver, locator).getCssValue(cssPropertyName);
    }
    public String convertRGBAToHexa(WebDriver driver, String locator){
        String backgroundColorRGBA = getWebElementCssValue(driver, locator,"background-color");
        return Color.fromString(backgroundColorRGBA).asHex();
    }
    public int getListElementSize(WebDriver driver, String locator){
        return getListWebElement(driver, locator).size();
    }
    /*
    * Apply for checkbox and radio button
    */
    public void checkToElement(WebDriver driver, String locator){
        if (!getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }
    /* Apply for checkbox only
    */
    public void uncheckToElement(WebDriver driver, String locator){
        if (getWebElement(driver, locator).isSelected()){
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver, locator).isDisplayed();
    }
    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }
    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    //Frame, iFrame
    public void switchToIframe(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXpath(locator)));
    }
    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    //User's actions on elements
    public void hoverToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(driver, locator)).perform();
    }
    public void doubleClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver, locator)).perform();
    }
    public void rightClickToElement(WebDriver driver, String locator){
        Actions action = new Actions(driver);
        action.contextClick(getWebElement(driver, locator)).perform();
    }
    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        Actions action = new Actions(driver);
        action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }
    public void sendKeyBoardToElement(WebDriver driver, String locator, Keys key){
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(driver, locator), key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor)driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor)driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
        String textActual = (String) ((JavascriptExecutor)driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor)driver).executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        boolean status = (boolean) ((JavascriptExecutor)driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
        return status;
    }

    //Wait
    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForListElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));
    }
    public void waitForElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }
    public void waitForListElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }
    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locator)));
    }
}
