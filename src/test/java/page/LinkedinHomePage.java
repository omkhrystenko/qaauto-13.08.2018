package page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

/**
 * LinkedinHomePage Object class.
 */
public class LinkedinHomePage extends LinkedinBasePage{
    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//input[@placeholder and @role='combobox']")
    private WebElement searchField;

    /**
     * Constructor for LinkedinHomePage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(profileNavItem, 10);
    }


    /**
     * Check is page loaded up on to controll parameters.
     */
    public boolean isPageLoaded() {
        waitUntilElementVisible(profileNavItem, 10);
        return getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && getCurrentTitle().contains("LinkedIn")
                && profileNavItem.isDisplayed();
    }

    /**
     * Search value using search field on the page.
     *
     * @param searchTerm - value that is searched.
     * @return page that loaded after search input submited.
     */
    public LinkedinSearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        return new LinkedinSearchPage(driver);
    }

    /**
     * Verify is controll element on the Home page loaded.
     *
     * @return result of verifying.
     */
    public boolean isProfileNavItemDisplayed(){
        boolean res = false;
        if(profileNavItem.isDisplayed()){
            res = true;
        }
        return res;
    }



}

