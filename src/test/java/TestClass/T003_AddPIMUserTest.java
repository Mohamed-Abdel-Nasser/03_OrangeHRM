package TestClass;

import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P001_Login;
import pages.P003_PIM;

public class T003_AddPIMUserTest extends TestBase {

    private static final String FirstName = "Mohamed";
    private static final String MidName = "Abdel Nasser";
    private static final String lastName = "Abbas";
    private static final String username = "Admin";
    private static final String password = "admin123";
    private static final String employee = "Mohamed";
    private P001_Login login;
    private P003_PIM.PIMSearch pimSearch;
    @BeforeMethod
    public void setUp() {
        login = new P001_Login(driver);
        pimSearch = new P003_PIM.PIMSearch(driver);
    }

    @Test
    public void addPIMUser() throws InterruptedException {
        login.setUsername(username).setPassword(password).clickLoginButton();
        pimSearch.ClickPIM().addUser().setFirstName(FirstName).setMiddleName(MidName).setLastName(lastName).saveButton();
        pimSearch.ClickPIM().setEmployee(employee).pressSearchButton();
        Assert.assertTrue(pimSearch.verifySuccessMessage("Id"), "not completed");
    }
}
