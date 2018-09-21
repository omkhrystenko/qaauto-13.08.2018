package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import page.LinkedinLoginPage;


/**
 *Common class for test group classes.
 */
public class LinkedinBaseTest {
    WebDriver driver;
    WebDriverWait driverWait;
    String mainURL;
    LinkedinLoginPage linkedinLoginPage;

    String browserName = "chrome";

    /**
     * Initiate system properties before test class run.
     */
        @BeforeClass
        public void setSystemProps(){
           // ReadProperties.readProperties();
           //h setSystemDriver();

        }

    /**
     * Make preconditions before each method run.
     */
        @Parameters("browserName")//позволяет подключить XML к методу или к тексту
        @BeforeMethod
        public void beforeMethod(@Optional("chrome") String browserName) throws Exception {//Аннотация @Optional позволяет запускать тесты как с ХМL так и с Идеи так как в случае отсутсвия параметра используется опшионал
            switch(browserName.toLowerCase()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    throw new Exception("Browser " + browserName + " is not supported.");
            }


            WebDriverManager.firefoxdriver().setup();
            //driver = new FirefoxDriver();
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
