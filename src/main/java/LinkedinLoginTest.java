import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    @Test
    public void successfulLoginTest() {
        //Navigate to 'Linkedin.com'
        //Verify that login page is loaded
        //Enter user e-mail
        //Enter user password
        //Click 'Sign in' button
        //Verify Home page is loaded

        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("autotestqa2018@gmail.com");
        userPasswordField.sendKeys("trust2018");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Home page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn", "Home page Title is wrong");

        WebElement profileNavItem = driver.findElement(By.xpath("//li[@id = 'profile-nav-item']"));
        Assert.assertTrue(profileNavItem.isDisplayed(), "profileNavItem button is not displayed on Home page");

        driver.quit();
    }

    @Test
    public void negativeloginTest(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("a@b.c");
        userPasswordField.sendKeys("wrong");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong");

        driver.quit();
    }
}






