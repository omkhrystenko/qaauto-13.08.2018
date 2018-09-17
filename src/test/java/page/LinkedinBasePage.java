package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * Common class for Page class group.
 */
public class LinkedinBasePage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected static GMailService gMailService = new GMailService();


    /**
     * Get current url.
     *
     * @return current Url String.
     */
    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * Get current title.
     *
     * @return current title String.
     */
    protected String getCurrentTitle(){
        return driver.getTitle();
    }

    /**
     * Check is page loaded up on to controll parameters.
     *
     * @param currentUrl - url of verifyed page.
     * @param currentTitle - title of verifyed page.
     * @param uniqElement - controll element on the verifyed page.
     * @return
     */
    protected boolean isPageLoaded(String currentUrl, String currentTitle, WebElement uniqElement){
        return  getCurrentUrl().equals(currentUrl)
                && getCurrentTitle().equals(currentTitle)
                && uniqElement.isDisplayed();

    }

    /**
     * Vait untill element is loaded on the page during set time.
     *
     * @param webElement - element that loaded on the page.
     * @param timeOutInSec - time how to wait.
     * @return loaded element.
     */
    protected WebElement waitUntilElementVisible(WebElement webElement, int timeOutInSec){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
