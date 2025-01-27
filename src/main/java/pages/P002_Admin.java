package pages;

import LOGGER.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageBase.PageBase;

import java.time.Duration;

public class P002_Admin {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final LogManager LOGGER = LogManager.getInstance();
    private final By ADMIN = By.linkText("Admin");
    private final By AddAdmin = By.xpath("//button[normalize-space()='Add']");
    private final By AdminDropDown = By.xpath("(//i[@data-v-bddebfba='' and @data-v-67d2aedf='' and contains(@class, 'oxd-icon bi-caret-down-fill oxd-select-text--arrow')])[1]");
    private final By AdminUser = By.xpath("//div[contains(text(),'Admin')]");
    private final By statusDropDown = By.xpath("(//div[@class='oxd-select-text-input'])[2]");  // (//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]
    private final By enabled = By.xpath("//div[@class='oxd-select-text-input' and text()='Enabled']");
    private final By employeeName = By.xpath("//input[@placeholder='Type for hints...']");
    private final By userName = By.xpath("(//input[contains(@class, 'oxd-input--active')] )[2]"); // (//input[@class='oxd-input oxd-input--active'])[2]
    private final By password = By.xpath("(//input[@class='oxd-input oxd-input--active' and @type='password' and @autocomplete='off'])[1]");
    private final By confirmPassword = By.xpath("(//input[@type='password'])[2]");         // //*[@id="app"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By searchField = By.xpath("(//input['.oxd-input.oxd-input--focus'])[2]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By record = By.xpath("//span[normalize-space()='(1) Record Found']");

    public P002_Admin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public P002_Admin clickAdminFromMenu() {
        try {
            LOGGER.info("Attempting to click the 'Admin' menu.");
            PageBase.explicitWait(driver, ADMIN);
            driver.findElement(ADMIN).click();
            LOGGER.info("Successfully clicked 'Admin' menu.");
        } catch (Exception e) {
            LOGGER.error("Error clicking the 'Admin' menu: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin setEmployee(String employeeNameTXT) {
        try {
            LOGGER.info("Entering employee name: {}" + employeeNameTXT);
            driver.findElement(employeeName).sendKeys(employeeNameTXT);
        } catch (Exception e) {
            LOGGER.error("Error setting employee name: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin SelectEmployeeName() throws InterruptedException {
        try {
            LOGGER.info("Selecting employee name from dropdown.");
            Thread.sleep(2000); // Consider using a more robust waiting method
            driver.findElement(this.employeeName).sendKeys(Keys.ARROW_DOWN);
            driver.findElement(this.employeeName).sendKeys(Keys.ENTER);
            LOGGER.info("Employee name selected.");
        } catch (Exception e) {
            LOGGER.error("Error selecting employee name: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin clickAddAdmin() {
        try {
            LOGGER.info("Attempting to click 'Add Admin' button.");
            driver.findElement(AddAdmin).click();
            LOGGER.info("Successfully clicked 'Add Admin' button.");
        } catch (Exception e) {
            LOGGER.error("Error clicking 'Add Admin' button: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin clickAdminUser() {
        try {
            LOGGER.info("Attempting to click 'Admin User' dropdown.");
            PageBase.explicitWait(driver, AdminDropDown);
            driver.findElement(AdminDropDown).click();
            LOGGER.info("Successfully clicked 'Admin User' dropdown.");
        } catch (Exception e) {
            LOGGER.error("Error clicking 'Admin User' dropdown: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin clickAdminDropdown() throws InterruptedException {
        try {
            LOGGER.info("Attempting to click Admin dropdown...");
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            LOGGER.info("Arrow Down key sent to dropdown.");
            Thread.sleep(2000);
            actions.sendKeys(Keys.ENTER).perform();
            LOGGER.info("Enter key sent to dropdown.");
        } catch (Exception e) {
            LOGGER.error("Failed to interact with Admin dropdown. Error: " + e.getMessage());
            throw e;
        }
        return this;
    }

    public P002_Admin clickStatusDropDown() {
        try {
            LOGGER.info("Attempting to click status dropdown.");
            wait.until(ExpectedConditions.elementToBeClickable(statusDropDown)).click();
            LOGGER.info("Successfully clicked status dropdown.");
        } catch (Exception e) {
            LOGGER.error("Error clicking status dropdown: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin clickEnabled() throws InterruptedException {
        try {
            LOGGER.info("Selecting 'Enabled' option.");
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            Thread.sleep(2000);
            actions.sendKeys(Keys.ENTER).perform();
            LOGGER.info("'Enabled' option selected.");
        } catch (Exception e) {
            LOGGER.error("Error selecting 'Enabled' option: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }


    public P002_Admin setUserName(String employeeUsername) {
        try {
            LOGGER.info("Entering username: {}" + employeeUsername);
            driver.findElement(userName).sendKeys(employeeUsername);
        } catch (Exception e) {
            LOGGER.error("Error setting username: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }


    public P002_Admin setPassword(String addPassword) {
        try {
            LOGGER.info("Entering password.");
            driver.findElement(password).sendKeys(addPassword);
            LOGGER.info("Password entered successfully.");
        } catch (Exception e) {
            LOGGER.error("Error setting password: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }


    public P002_Admin setConfirmPassword(String addPassword) {
        try {
            LOGGER.info("Entering confirm password.");
            PageBase.explicitWait(driver, confirmPassword);
            driver.findElement(confirmPassword).sendKeys(addPassword);
            LOGGER.info("Confirm password entered successfully.");
        } catch (Exception e) {
            LOGGER.error("Error setting confirm password: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin searchAddedUser(String employeeUsername) {
        try {
            LOGGER.info("Searching for user with username: {}" + employeeUsername);
            PageBase.explicitWait(driver, searchField);
            driver.findElement(searchField).sendKeys(employeeUsername);
        } catch (Exception e) {
            LOGGER.error("Error searching for user: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin pressSearchButton() throws InterruptedException {
        try {
            LOGGER.info("Pressing search button.");
            Thread.sleep(2000);
            driver.findElement(searchButton).click();
            LOGGER.info("Search button clicked.");
        } catch (Exception e) {
            LOGGER.error("Error clicking search button: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    public P002_Admin save() throws InterruptedException {
        try {
            LOGGER.info("Attempting to click 'Save' button.");
            Thread.sleep(3000); // Consider using a more robust waiting method
            driver.findElement(saveButton).click();
            Thread.sleep(6000); // Consider reducing wait time with more effective waits
            LOGGER.info("'Save' button clicked successfully.");
        } catch (Exception e) {
            LOGGER.error("Error clicking 'Save' button: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }


    public boolean verifySuccessMessage(String oneRecord) {
        try {
            LOGGER.info("Verifying success message.");
            boolean isMessageCorrect = driver.findElement(record).getText().contains(oneRecord);
            LOGGER.info("Success message verification result: {}" + isMessageCorrect);
            return isMessageCorrect;
        } catch (Exception e) {
            LOGGER.error("Error verifying success message: " + e.getMessage() + e);
            throw e;
        }
    }
}
