package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import page.LinkedinLoginPage;
import util.GMailService;


/**
 *Common class for test group classes.
 */
public class LinkedinBaseTest {
    WebDriver driver;
    WebDriverWait driverWait;
    String mainURL;
    LinkedinLoginPage linkedinLoginPage;


    /**
     * Initiate system properties before test class run.
     */
        @BeforeClass
        public void setSystemProps(){
            System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");
        }

    /**
     * Make preconditions before each method run.
     */
        @BeforeMethod
        public void beforeMethod(){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            mainURL = "https://www.linkedin.com/";
            driver.get(mainURL);
            driverWait = new WebDriverWait(driver, 10);

        linkedinLoginPage = new LinkedinLoginPage(driver);

    }

    /**
     * Make postconditions after each method.
     */
    @AfterMethod(alwaysRun = true) //параметр (alwaysRun = true) позволяет отработатся афтер даже после выброса Exception
    public void afterMethod(){
       driver.quit();
    }

}
