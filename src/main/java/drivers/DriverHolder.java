package drivers;

import LOGGER.LogManager;
import org.openqa.selenium.WebDriver;

public class DriverHolder {
    private static final LogManager LOGGER = LogManager.getInstance();

    // ThreadLocal to store WebDriver instances for each thread separately
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /*
    TODO: The `getDriver` method retrieves the WebDriver instance for the current thread.
          It ensures that each thread gets its own WebDriver instance when running tests in parallel.
          It performs the following steps:
        1. Returns the WebDriver instance associated with the current thread using `driver.get()`.
        2. If no instance is found, it will return `null`, so make sure to check for `null` in your tests.
    */
    public static WebDriver getDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver == null) {
            LOGGER.warn("No WebDriver instance found for the current thread.");
        }
        return currentDriver;
    }

    /*
    TODO: The `setDriver` method associates a WebDriver instance with the current thread.
          It ensures that each thread running tests has its own isolated WebDriver instance.
          It performs the following steps:
        1. Sets the WebDriver instance for the current thread using `driver.set(driverInstance)`.
        2. Logs the action of setting the WebDriver instance.
    */
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
        LOGGER.info("WebDriver instance set for the current thread.");
    }

    /*
    TODO: The `removeDriver` method removes the WebDriver instance associated with the current thread.
          It is important to call this method after the test execution to clean up resources.
          It performs the following steps:
        1. Removes the WebDriver instance for the current thread using `driver.remove()`.
        2. Logs the action of removing the WebDriver instance.
    */
    public static void removeDriver() {
        driver.remove();
        LOGGER.info("WebDriver instance removed for the current thread.");
    }
}
