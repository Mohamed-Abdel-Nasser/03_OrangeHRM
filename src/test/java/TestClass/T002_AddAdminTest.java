package TestClass;

import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P002_Admin;
import pages.P001_Login;

import static drivers.DriverHolder.getDriver;
import static util.Utility.generateUsername;

public class T002_AddAdminTest extends TestBase {
    String NewEmployeeName_fakerDate = faker.regexify("[aef]");
    String employeeUsername = generateUsername(8, 10);
    String addPassword = "P@ssword1";
    String username = "Admin";
    String password = "admin123";
    private P001_Login login;
    private P002_Admin admin;

    @BeforeMethod
    public void setUp() {
        login = new P001_Login(driver);
        admin = new P002_Admin(driver);
    }

    @Test
    public void AddAdminWithValidData_P() throws InterruptedException {
        login.setUsername(username).setPassword(password).clickLoginButton();
        admin.clickAdminFromMenu().clickAddAdmin();
        admin.setUserName(employeeUsername).SelectEmployeeName().setPassword(addPassword).setConfirmPassword(addPassword);
        admin.setEmployee(NewEmployeeName_fakerDate).SelectEmployeeName().clickAdminUser().clickAdminDropdown().clickStatusDropDown().clickEnabled().save();
        Assert.assertEquals(getDriver().getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        admin.searchAddedUser(employeeUsername).pressSearchButton();
        Assert.assertTrue(admin.verifySuccessMessage("(1) Record Found"), "not completed");
    }
}
