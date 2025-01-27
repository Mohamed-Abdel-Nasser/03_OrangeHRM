package pages.PageBase;

import LOGGER.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    private static final LogManager LOGGER = LogManager.getInstance();
    WebDriver driver;

    private final By SEARCH_BOX = By.xpath("//input[@id='small-searchterms']");

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public PageBase searchFunction(String keyword) {
        try {
            LOGGER.info("Attempting to search for keyword: " + keyword);
            driver.findElement(this.SEARCH_BOX).sendKeys(keyword, Keys.ENTER);
            LOGGER.info("Search for keyword: " + keyword + " initiated.");
        } catch (Exception e) {
            LOGGER.error("Failed to perform search with keyword: " + keyword + ". Error: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public String checkUrl() {
        try {
            String currentUrl = driver.getCurrentUrl();
            LOGGER.info("Current URL: " + currentUrl);
            return currentUrl;
        } catch (Exception e) {
            LOGGER.error("Failed to fetch current URL. Error: " + e.getMessage());
            throw e;
        }
    }

    public PageBase scrollDown() {
        try {
            LOGGER.info("Attempting to scroll down the page...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,360)", "");
            LOGGER.info("Page scrolled down successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to scroll down the page. Error: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public static void explicitWait(WebDriver driver, By element) {
        try {
            LOGGER.info("Waiting for element to be visible: " + element);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            LOGGER.info("Element is now visible: " + element);
        } catch (Exception e) {
            LOGGER.error("Failed to wait for element visibility. Error: " + e.getMessage());
            throw e;
        }
    }

    public PageBase Wait() throws InterruptedException {
        try {
            LOGGER.info("Waiting for 1300 ms...");
            Thread.sleep(1300);
            LOGGER.info("Wait completed.");
        } catch (InterruptedException e) {
            LOGGER.error("Wait interrupted. Error: " + e.getMessage());
            throw e;
        }
        return this;
    }

}
