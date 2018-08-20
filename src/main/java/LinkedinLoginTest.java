import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

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


        String loginMail = "";

        String password = "";

        WebElement loginMailInput = driver.findElement(By.id("login-email"));

        WebElement passwordInput = driver.findElement(By.id("login-password"));

        WebElement signInInput = driver.findElement(By.id("login-submit"));



        loginMailInput.clear();
        loginMailInput.click();
        loginMailInput.sendKeys(loginMail);

        passwordInput.clear();
        passwordInput.click();
        passwordInput.sendKeys(password);

        signInInput.click();

        WebElement me = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"nav-settings__dropdown-trigger\"]/div/span[1]")));
        String meWebElement =  me.getText();
        String controlString = "Me";

        Assert.assertEquals(meWebElement, controlString);

        driver.quit();


    }


}






