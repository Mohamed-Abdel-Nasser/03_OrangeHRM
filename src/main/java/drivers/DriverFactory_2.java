package drivers;

import LOGGER.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * DriverFactory_2
 *
 * This class provides an implementation of a WebDriver factory for initializing
 * and retrieving WebDriver instances based on different browsers.
 *
 * Note: The class `DriverFactory_1` represents an alternative implementation of
 * this factory, showcasing another approach to achieve similar functionality.
 * Both classes can be used interchangeably depending on the specific requirements
 * or design preferences.
 *
 * Ensure that only one factory class is used throughout the project to avoid
 * inconsistencies in WebDriver handling.
 */

public class DriverFactory_2 {

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


    public enum Browser {
        CHROME, FIREFOX, EDGE
    }

    public static WebDriver initializeDriver(Browser browserName) {
        if (Objects.nonNull(driverThread.get())) {
            LOGGER.warn("WebDriver is already initialized. Skipping reinitialization.");
            return driverThread.get();
        }

        WebDriver driver = null;

        try {
            switch (browserName) {
                case CHROME:
                    LOGGER.info("Initializing Chrome Driver...");
                    driver = createChromeDriver();
                    break;

                case FIREFOX:
                    LOGGER.info("Initializing Firefox Driver...");
                    driver = createFirefoxDriver();
                    break;

                case EDGE:
                    LOGGER.info("Initializing Edge Driver...");
                    driver = createEdgeDriver();
                    break;

                default:
                    String errorMessage = "Unsupported browser: " + browserName;
                    LOGGER.error(errorMessage);
                    throw new IllegalArgumentException(errorMessage);
            }

            LOGGER.info("Driver initialized successfully for browser: " + browserName);

        } catch (Exception e) {
            String errorMessage = "Error occurred while initializing the WebDriver for browser: " + browserName;
            LOGGER.error(errorMessage + e);
            throw new RuntimeException(errorMessage, e);
        }

        return driver;
    }

    public static WebDriver createChromeDriver() {
        try {
            LOGGER.info("Creating Chrome Driver with custom options...");
            ChromeOptions options = new ChromeOptions();
            configureCommonOptions(options);
            LOGGER.info("Chrome Driver options: " + options.toString());
            return new ChromeDriver(options);
        } catch (Exception e) {
            String errorMessage = "Failed to create Chrome Driver: " + e.getMessage();
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public static WebDriver createFirefoxDriver() {
        try {
            LOGGER.info("Creating Firefox Driver with custom options...");
            FirefoxOptions options = new FirefoxOptions();
            configureCommonOptions(options);
            LOGGER.info("Firefox Driver options: " + options.toString());
            return new FirefoxDriver(options);
        } catch (Exception e) {
            String errorMessage = "Failed to create Firefox Driver: " + e.getMessage();
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public static WebDriver createEdgeDriver() {
        try {
            LOGGER.info("Creating Edge Driver with custom options...");
            EdgeOptions options = new EdgeOptions();
            configureCommonOptions(options);
            LOGGER.info("Edge Driver options: " + options.toString());
            return new EdgeDriver(options);
        } catch (Exception e) {
            String errorMessage = "Failed to create Edge Driver: " + e.getMessage();
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private static <T> void configureCommonOptions(T options) {
        try {
            if (options instanceof ChromeOptions) {
                ChromeOptions chromeOptions = (ChromeOptions) options;
                Map<String, Object> prefs = new HashMap<>();

                // Chrome preferences
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.default_content_setting_values.notifications", 2);

                // Chrome options Experimental Options
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                // Chrome options Arguments
                chromeOptions.addArguments(
                        "--start-maximized",
                        "--incognito",
                        "--disable-web-security",
                        "--no-proxy-server",
                        "--disable-notifications",
                        "--remote-allow-origins=*",
                        "--disable-blink-features=AutomationControlled"
                );

                // Chrome capabilities
                chromeOptions.setCapability("browserName", "chrome");
                chromeOptions.setCapability("platformName", "ANY");

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                chromeOptions.merge(capabilities);

                LOGGER.info("Chrome options configured: " + chromeOptions.toString());
            } else if (options instanceof FirefoxOptions) {
                FirefoxOptions firefoxOptions = (FirefoxOptions) options;

                // Firefox options Arguments
                firefoxOptions.addArguments(
                        "-width=1920",
                        "-height=1080",
                        "--disable-web-security",
                        "--no-proxy-server",
                        "--disable-notifications"
                );

                // Firefox capabilities
                firefoxOptions.setCapability("browserName", "firefox");
                firefoxOptions.setCapability("platformName", "ANY");
                firefoxOptions.setCapability("acceptInsecureCerts", true);

                LOGGER.info("Firefox options configured: " + firefoxOptions.toString());
            } else if (options instanceof EdgeOptions) {
                EdgeOptions edgeOptions = (EdgeOptions) options;

                // Edge options Arguments
                edgeOptions.addArguments(
                        "--start-maximized",
                        "--disable-web-security",
                        "--no-proxy-server",
                        "--disable-notifications",
                        "--remote-allow-origins=*"
                );

                // Edge capabilities
                edgeOptions.setCapability("browserName", "edge");
                edgeOptions.setCapability("platformName", "ANY");
                edgeOptions.setCapability("acceptInsecureCerts", true);

                LOGGER.info("Edge options configured: " + edgeOptions.toString());
            }

        } catch (Exception e) {
            String errorMessage = "Failed to configure options: " + e.getMessage();
            LOGGER.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }
}
