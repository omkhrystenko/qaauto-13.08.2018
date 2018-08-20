import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BadCodeExample {


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait driverWait = new WebDriverWait(driver, 30);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String urlText = "https://www.google.com.ua/";
        String idSearchField = "lst-ib";
        String searchWord = "skillup";

        driver.get(urlText);
        WebElement searchField = driver.findElement(By.id(idSearchField));

        driverWait.until(ExpectedConditions.elementToBeClickable(searchField)).click();
        searchField.clear();
        searchField.sendKeys(searchWord);
        searchField.submit();

        driver.quit();
    }
}
