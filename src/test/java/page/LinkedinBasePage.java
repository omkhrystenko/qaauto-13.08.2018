package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinBasePage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;




    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }

    public boolean isPageLoaded(String currentUrl, String currentTitle, WebElement uniqElement){
        return  getCurrentUrl().equals(currentUrl)
                && getCurrentTitle().equals(currentTitle)
                && uniqElement.isDisplayed();

    }
}
