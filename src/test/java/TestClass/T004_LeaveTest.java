package TestClass;

import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P001_Login;
import pages.P004_Leave;

import static drivers.DriverHolder.getDriver;

public class T004_LeaveTest extends TestBase {
    private static final String username = "Admin";
    private static final String password = "admin123";
    private P001_Login login;
    private P004_Leave leave;

    @BeforeMethod
    public void setUp() {
        login = new P001_Login(driver);
        leave = new P004_Leave(getDriver());
    }

    @Test(priority = 1, description = "Login to System with Valid Data")
    public void verifyLeaveTextVisibilityAfterLogin_P() {
        login.setUsername(username).setPassword(password).clickLoginButton();
        leave.Click_Leave_MenuButton();
        Assert.assertTrue(leave.isLeaveTextVisible());
    }
}
