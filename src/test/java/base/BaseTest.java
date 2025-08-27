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
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    
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
    	
        WebDriver localDriver = new ChromeDriver(options);
        driver.set(localDriver);
        
        getDriver().manage().window().maximize();
        getDriver().get("https://practicetestautomation.com/practice-test-login/");
    }

    @AfterMethod
    public void teardown() {
        if (getDriver() != null) {
        	getDriver().quit();
        	driver.remove();
        }
        test.remove();
    }
    
    @AfterSuite
    public void tearDownReport() {
        extent.flush(); // Very important
    }
    
    public WebDriver getDriver() {
        return driver.get();
    }

    public void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public ExtentTest getTest() {
        return test.get();
    }
}
