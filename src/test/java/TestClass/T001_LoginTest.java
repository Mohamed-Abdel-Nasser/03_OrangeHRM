package TestClass;

import TestBase.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P001_Login;

public class T001_LoginTest extends TestBase {

    private static final String username = "Admin";
    private static final String password = "admin123";
    private static P001_Login loginPage;

    @BeforeMethod
    public void setUp() {
        loginPage = new P001_Login(driver);
    }

    @Test(priority = 1, description = "Login with Valid Username and Password")
    public void loginWithValidData_P() {
        loginPage.setUsername(username).setPassword(password).clickLoginButton();
    }
}
