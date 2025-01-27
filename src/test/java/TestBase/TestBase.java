package TestBase;

import com.github.javafaker.Faker;
import drivers.DriverFactory_1;
import drivers.DriverFactory_2;
import drivers.DriverHolder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestBase {
    protected static WebDriver driver;
    protected Faker faker = new Faker();

    @Parameters("browser")
    @BeforeTest
    public void setupDriver(@Optional("chrome") String browser) throws Exception {
//        MyScreenRecorder.startRecording("Sprint1");
        driver = DriverFactory_1.initializeDriver(browser);
//        driver = DriverFactory_2.initializeDriver(DriverFactory_2.Browser.valueOf(browser.toLowerCase()));
        DriverHolder.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterTest
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
//        MyScreenRecorder.stopRecording();
    }
}
