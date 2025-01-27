package TestClass;

import TestBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.P001_Login;
import pages.PageBase.PageBase;
import pages.P005_Recruitment;

public class T005_RecruitmentTest extends TestBase {

    private static final String username = "Admin";
    private static final String password = "admin123";
    private static P001_Login loginPage;
    private static PageBase pageBase;
    private static P005_Recruitment recruitment;

    @BeforeMethod

    public void setUp() {
        loginPage = new P001_Login(driver);
        pageBase = new PageBase(driver);
        recruitment = new P005_Recruitment(driver);
    }

    @Test(priority = 1, description = "Login to System with Valid Data")
    public void navigateToRecruitmentPage_P() throws InterruptedException {
        loginPage.setUsername(username).setPassword(password).clickLoginButton();
        pageBase.Wait();
        recruitment.click_link();
        pageBase.Wait();
        Assert.assertEquals(recruitment.verify_url(), "https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates");
        Assert.assertTrue(recruitment.verify_title(), "navigation faild");
    }
}
