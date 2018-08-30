import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public BasePage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
    }

    public String getCurrentURL(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }

    public boolean isPageLoaded(String currentUrl, String currentTitle, WebElement uniqElement){
        return  getCurrentURL().equals(currentUrl)
                && getCurrentTitle().equals(currentTitle)
                && uniqElement.isDisplayed();

    }
}
