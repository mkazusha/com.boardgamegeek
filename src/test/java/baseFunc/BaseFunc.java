package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {

    private WebDriver driver;
    private WebDriverWait wait;

    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    public void openPage(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);
    }

    public WebElement getElement(By locator) {
        Assertions.assertFalse(isElementPresent(locator), "Element is not found!");
        return driver.findElement(locator);
    }

    public List<WebElement> getAllElements(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty());
        return driver.findElements(locator);
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public boolean isElementPresent(By locator) {
        waitForElement(locator);
        return getAllElements(locator).isEmpty();
    }

    public String getPageId() {
        String id = "";
        String[] split = driver.getCurrentUrl().split("\\/+");
        for (String s : split) {
            if (s.matches("\\d+")) {
                id = s;
                break;
            }
        }
        return id;
    }

    public void closeBrowser() {
        driver.quit();
    }
}
