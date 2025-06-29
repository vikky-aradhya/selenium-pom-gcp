package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import utils.ExtentManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    
    @BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--headless"); 
    	options.addArguments("--no-sandbox");
    	options.addArguments("--disable-dev-shm-usage");
    	options.addArguments("--disable-gpu"); 
    	options.addArguments("--remote-allow-origins=*");
    	options.addArguments("--window-size=1920,1080");
    	
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterSuite
    public void tearDownReport() {
        extent.flush(); // Very important
    }
}
