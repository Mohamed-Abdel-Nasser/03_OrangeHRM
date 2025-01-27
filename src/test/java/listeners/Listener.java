package listeners;

import LOGGER.LogManager;
import common.MyScreenRecorder;
import drivers.DriverFactory_1;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Listener implements ITestListener {

    private static final LogManager LOGGER = LogManager.getInstance();
    private WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test passed: " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        LOGGER.error("Test failed: " + result.getMethod().getMethodName());
        takeScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.warn("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LOGGER.warn("Test failed but within success percentage: " + result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        LOGGER.info("Test execution started: " + context.getName());
        try {
            MyScreenRecorder.startRecording(context.getName());
            LOGGER.info("Screen recording started for context: " + context.getName());
        } catch (Exception e) {
            LOGGER.error("Failed to start screen recording. Error: " + e.getMessage() + e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        LOGGER.info("Test execution finished: " + context.getName());
        try {
            MyScreenRecorder.stopRecording();
            LOGGER.info("Screen recording stopped for context: " + context.getName());
        } catch (Exception e) {
            LOGGER.error("Failed to stop screen recording. Error: " + e.getMessage() + e);
        }
    }

    public void takeScreenshot() {
        driver = DriverFactory_1.getDriver();
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        Date currentDate = new Date();
        String screenshotName = currentDate.toString().replace(" ", "_").replace(":", "-");
        File screenshotFile = new File(System.getProperty("user.dir") + "/src/test/resources/Screenshots/" + screenshotName + ".png");
        try {
            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), screenshotFile);
            LOGGER.info("Screenshot taken: " + screenshotFile.getAbsolutePath());
        } catch (WebDriverException | IOException e) {
            LOGGER.error("Failed to take screenshot. Error: " + e.getMessage() + e);
        }
    }
}
