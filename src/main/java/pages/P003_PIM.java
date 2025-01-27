package pages;

import LOGGER.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.PageBase.PageBase;

public class P003_PIM {
    WebDriver driver;
    private final By firstName = By.xpath("//input[@placeholder='First Name']");
    private final By MiddleName = By.xpath("//input[@placeholder='Middle Name']");
    private final By LastName = By.xpath("//input[@placeholder='Last Name']");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private static final LogManager LOGGER = LogManager.getInstance();

    public P003_PIM(WebDriver driver) {
        this.driver = driver;
    }


    // Set first name
    public P003_PIM setFirstName(String FirstName) {
        try {
            LOGGER.info("Setting first name: " + FirstName);
            driver.findElement(firstName).sendKeys(FirstName);
            LOGGER.info("First name set successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to set first name. Error: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }


    // Set middle name
    public P003_PIM setMiddleName(String MidName) {
        try {
            LOGGER.info("Setting middle name: " + MidName);
            driver.findElement(MiddleName).sendKeys(MidName);
            LOGGER.info("Middle name set successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to set middle name. Error: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }


    // Set last name
    public P003_PIM setLastName(String lastName) {
        try {
            LOGGER.info("Setting last name: " + lastName);
            driver.findElement(LastName).sendKeys(lastName);
            LOGGER.info("Last name set successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to set last name. Error: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

    // Click save button
    public P003_PIM saveButton() throws InterruptedException {
        try {
            LOGGER.info("Attempting to click the save button...");
            Thread.sleep(3000); // Temporary delay for testing purposes
            driver.findElement(saveButton).click();
            Thread.sleep(5000); // Temporary delay for testing purposes
            LOGGER.info("Save button clicked successfully.");
        } catch (Exception e) {
            LOGGER.error("Failed to click the save button. Error: " + e.getMessage() + e);
            throw e;
        }
        return this;
    }

   public static class PIMSearch {
        WebDriver driver;
        private final By EmployeeName = By.xpath("(//input['Type for hints...'])[2]");
        private final By searchButton = By.xpath("//button[@type='submit']");
        private final By addButton = By.xpath("//button[normalize-space()='Add']");
        private final By PIM = By.linkText("PIM");
        private final By record = By.xpath("//body/div[@id='app']/div[@class='oxd-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-paper-container']/div[@class='orangehrm-container']/div[@role='table']/div[@role='rowgroup']/div[@role='row']/div[2]");
        private static final LogManager LOGGER = LogManager.getInstance();

        public PIMSearch(WebDriver driver) {
            this.driver = driver;
        }

        public PIMSearch setEmployee(String employee) {
            try {
                LOGGER.info("Waiting for Employee Name input to be visible...");
                PageBase.explicitWait(driver, EmployeeName);
                LOGGER.info("Setting Employee Name: " + employee);
                driver.findElement(EmployeeName).sendKeys(employee);
                LOGGER.info("Employee Name set successfully.");
            } catch (Exception e) {
                LOGGER.error("Failed to set Employee Name. Error: " + e.getMessage() + e);
                throw e;
            }
            return this;
        }

        public PIMSearch pressSearchButton() {
            try {
                LOGGER.info("Waiting for Search button to be clickable...");
                PageBase.explicitWait(driver, searchButton);
                LOGGER.info("Clicking the Search button.");
                driver.findElement(searchButton).click();
                LOGGER.info("Search button clicked successfully.");
            } catch (Exception e) {
                LOGGER.error("Failed to click the Search button. Error: " + e.getMessage() + e);
                throw e;
            }
            return this;
        }

        public P003_PIM addUser() {
            try {
                LOGGER.info("Clicking the Add button to add a new user.");
                driver.findElement(addButton).click();
                LOGGER.info("Navigated to Add User page successfully.");
            } catch (Exception e) {
                LOGGER.error("Failed to click the Add button. Error: " + e.getMessage() + e);
                throw e;
            }
            return new P003_PIM(driver);
        }

        public PIMSearch ClickPIM() {
            try {
                LOGGER.info("Clicking the PIM link.");
                driver.findElement(PIM).click();
                LOGGER.info("PIM link clicked successfully.");
            } catch (Exception e) {
                LOGGER.error("Failed to click the PIM link. Error: " + e.getMessage() + e);
                throw e;
            }
            return this;
        }

        public boolean verifySuccessMessage(String oneRecord) {
            try {
                LOGGER.info("Verifying if the success message contains: " + oneRecord);
                boolean result = driver.findElement(record).getText().contains(oneRecord);
                LOGGER.info("Success message verification result: " + result);
                return result;
            } catch (Exception e) {
                LOGGER.error("Failed to verify the success message. Error: " + e.getMessage() + e);
                throw e;
            }
        }
    }
}
