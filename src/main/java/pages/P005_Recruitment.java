package pages;

import LOGGER.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P005_Recruitment {

    private static final LogManager LOGGER = LogManager.getInstance();
    WebDriver driver;

    private final By LINK = By.xpath("//span[normalize-space()='Recruitment']");

    public P005_Recruitment(WebDriver driver) {
        this.driver = driver;
    }
    public P005_Recruitment click_link() {
        try {
            LOGGER.info("Clicking the Recruitment link.");
            driver.findElement(this.LINK).click();
            LOGGER.info("Recruitment link clicked successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to click the Recruitment link. Error: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }
    public String verify_url() {
        try {
            LOGGER.info("Verifying the current URL.");
            String currentUrl = driver.getCurrentUrl();
            LOGGER.info("Current URL: " + currentUrl);
            return currentUrl;
        } catch (Exception e) {
            LOGGER.error("Failed to verify the current URL. Error: " + e.getMessage() + e);
            throw e;
        }
    }
    public boolean verify_title() {
        try {
            LOGGER.info("Verifying the Recruitment page title.");
            boolean isDisplayed = driver.findElement(By.xpath("//h6[normalize-space()='Recruitment']")).isDisplayed();
            LOGGER.info("Recruitment page title is displayed: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            LOGGER.error("Failed to verify the Recruitment page title. Error: " + e.getMessage() + e);
            throw e;
        }
    }

}
