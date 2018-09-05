import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class LinkedinBaseTest {
    WebDriver driver;
    WebDriverWait driverWait;
    String mainURL;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeClass
    public void setSystemProps(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        mainURL = "https://www.linkedin.com/";
        driver.get(mainURL);
        driverWait = new WebDriverWait(driver, 10);
        linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
    }

    @AfterMethod
    public void afterMethod(){
       // driver.quit();
    }

}
