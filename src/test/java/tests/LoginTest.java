package tests;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess() {
    	setTest(extent.createTest("testLoginSuccess - " + Thread.currentThread().getId()));
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("student", "Password123");
        
        try {
            Assert.assertTrue(loginPage.getLoginMessage().contains("Congratulations"));
            getTest().pass("Login successful");
        } catch (AssertionError e) {
            getTest().fail("Login failed");
            throw e;
        }
    }
}
