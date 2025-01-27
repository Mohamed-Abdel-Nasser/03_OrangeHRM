package pages;

import LOGGER.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PageBase.PageBase;

public class P001_Login extends PageBase {
    private static final LogManager LOGGER = LogManager.getInstance();
    WebDriver driver;
    private final By USERNAME = By.xpath("//input[@placeholder='Username']");
    private final By PASSWORD = By.xpath("//input[@placeholder='Password']");
    private final By LoginBUTTON = By.xpath("//button[@type='submit']");


    public P001_Login(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public P001_Login setUsername(String username) {
        try {
            LOGGER.info("Entering username: " + username);
            driver.findElement(USERNAME).sendKeys(username);
        } catch (Exception e) {
            LOGGER.error("Failed to enter username. Error: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public P001_Login setPassword(String password) {
        try {
            LOGGER.info("Entering password.");
            driver.findElement(PASSWORD).sendKeys(password);
        } catch (Exception e) {
            LOGGER.error("Failed to enter password. Error: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public P001_Login clickLoginButton() {
        try {
            LOGGER.info("Clicking the login button...");
            driver.findElement(LoginBUTTON).click();
            LOGGER.info("Login button clicked successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to click the login button. Error: " + e.getMessage());
            throw e;
        }
        return this;
    }
}
