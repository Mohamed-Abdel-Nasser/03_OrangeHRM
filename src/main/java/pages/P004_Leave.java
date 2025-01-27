package pages;

import LOGGER.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class P004_Leave {
    // TODO: Step1: Constructor
    WebDriver driver;
    private static final LogManager LOGGER = LogManager.getInstance();
    private final By Leave_MenuButton = By.xpath("(//li[@class='oxd-main-menu-item-wrapper'])[3]");
    private final By Leave_TEXT = By.xpath("//h6[text()='Leave']");

    public P004_Leave(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public P004_Leave Click_Leave_MenuButton() {
        try {
            LOGGER.info("Attempting to click the 'Leave' menu button...");
            driver.findElement(this.Leave_MenuButton).click();
            LOGGER.info("'Leave' menu button clicked successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to click the 'Leave' menu button. Error: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public boolean isLeaveTextVisible() {
        try {
            LOGGER.info("Checking if the 'Leave' text is visible...");
            boolean isVisible = driver.findElement(this.Leave_TEXT).isDisplayed();
            LOGGER.info("Is 'Leave' text visible? " + isVisible);
            return isVisible;
        } catch (Exception e) {
            LOGGER.error("Failed to check 'Leave' text visibility. Error: " + e.getMessage());
            throw e;
        }
    }
}
