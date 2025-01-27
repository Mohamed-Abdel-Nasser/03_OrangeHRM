package drivers;

import LOGGER.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DriverFactory_1 {

    private static final LogManager LOGGER = LogManager.getInstance();
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    // Method to retrieve the current WebDriver instance
    public static WebDriver getDriver() {
        LOGGER.info("Attempting to retrieve the WebDriver instance.");
        if (Objects.isNull(driverThread.get())) {
            String errorMessage = "WebDriver instance is not initialized. Ensure initializeDriver() is called before using getDriver().";
            LOGGER.error(errorMessage);
            throw new IllegalStateException(errorMessage);
        }
        LOGGER.info("WebDriver instance retrieved successfully.");
        return driverThread.get();
    }

    public static WebDriver initializeDriver(String browserName) {
        if (Objects.nonNull(driverThread.get())) {
            LOGGER.warn("WebDriver is already initialized. Skipping reinitialization.");
            return driverThread.get();
        }
        WebDriver driver = null;
        try {
            LOGGER.info("Initializing WebDriver for browser: " + browserName);

            // Create a new ChromeOptions object
            ChromeOptions chromeOptions = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();

            // Chrome preferences to prevent credential storage and notifications
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_setting_values.notifications", 2);

            // Add Chrome-specific arguments
            chromeOptions.addArguments(
                    "--disable-blink-features=AutomationControlled",
                    "user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36",
                    "--start-maximized",
                    "--incognito",
                    "--disable-web-security",
                    "--no-proxy-server",
                    "--disable-notifications",
                    "--remote-allow-origins=*"
            );

            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.setExperimentalOption("prefs", prefs);
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

            // Log Chrome options
            LOGGER.info("Chrome options: " + chromeOptions.toString());

            // Switch based on browser type
            switch (browserName.toLowerCase()) {
                case "chrome":
                    LOGGER.info("Creating Chrome Driver with options...");
                    driver = new ChromeDriver(chromeOptions);
                    LOGGER.info("Chrome Driver initialized successfully.");
                    break;

                case "firefox":
                    LOGGER.info("Creating Firefox Driver with options...");
                    FirefoxBinary firefoxBinary = new FirefoxBinary();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary(firefoxBinary);
                    firefoxOptions.addArguments(
                            "--window-size=1280x720",
                            "--incognito",
                            "--disable-web-security",
                            "--no-proxy-server",
                            "--disable-notifications",
                            "--remote-allow-origins=*",
                            "--disable-blink-features=AutomationControlled",
                            "--disable-extensions",
                            "--disable-gpu",
                            "--start-maximized",
                            "--disable-software-rasterizer",
                            "--no-sandbox",
                            "--safe-mode"
                    );
                    // Log Firefox options
                    LOGGER.info("Firefox options: " + firefoxOptions.toString());
                    driver = new FirefoxDriver(firefoxOptions);
                    LOGGER.info("Firefox Driver initialized successfully.");
                    break;

                case "edge":
                    LOGGER.info("Creating Edge Driver with options...");
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments(
                            "--start-maximized",
                            "--incognito",
                            "--disable-web-security",
                            "--no-proxy-server",
                            "--disable-notifications",
                            "--remote-allow-origins=*",
                            "--disable-blink-features=AutomationControlled",
                            "--disable-extensions",
                            "--disable-gpu",
                            "--disable-software-rasterizer",
                            "--no-sandbox",
                            "--safe-mode"
                    );
                    // Log Edge options
                    LOGGER.info("Edge options: " + edgeOptions.toString());
                    driver = new EdgeDriver(edgeOptions);
                    LOGGER.info("Edge Driver initialized successfully.");
                    break;

                default:
                    LOGGER.warn("No matching browser found. Defaulting to Chrome.");
                    driver = new ChromeDriver(chromeOptions);
                    LOGGER.info("Chrome Driver initialized successfully as fallback.");
                    break;
            }

        } catch (Exception e) {
            String errorMessage = "Error occurred while initializing the WebDriver for browser: " + browserName;
            LOGGER.error(errorMessage + e);
            throw new RuntimeException(errorMessage, e);
        }

        return driver;
    }
}
