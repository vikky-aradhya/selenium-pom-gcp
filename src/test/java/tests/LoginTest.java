package tests;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess() {
    	test = extent.createTest("testLoginSuccess");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("student", "Password123");
        
        try {
            Assert.assertTrue(loginPage.getLoginMessage().contains("Congratulations"));
            test.pass("Login successful");
        } catch (AssertionError e) {
            test.fail("Login failed");
            throw e;
        }
    }
}
