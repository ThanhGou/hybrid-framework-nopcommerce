package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver driver;
    protected WebDriver getBrowserDriver(String browserName){
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Browser is not valid.");
        }
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().setSize(new Dimension(1920, 1082));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/");
        return driver;
    }
    protected String getRandomEmail(){
        Random rand = new Random();
        return "test" + rand.nextInt(1000) + "@gmail.com";
    }
    protected void closeBrowser(){
        if (driver == null){
            System.out.println("Browser is closed.");
        }else {
            driver.quit();
        }
    }
}
