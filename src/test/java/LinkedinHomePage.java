import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinHomePage extends LinkedinBasePage {
    @FindBy(xpath = "//li[@id = 'profile-nav-item']")
    private WebElement profileNavItem;

    public LinkedinHomePage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public boolean isProfileNavItemDisplayed(){
        boolean res = false;
        if(profileNavItem.isDisplayed()){
            res = true;
        }
        return res;
    }

    public boolean isPageLoaded(){
        String currentURL_Home = "https://www.linkedin.com/feed/";
        String currentTitle_Home = "LinkedIn";
        return isPageLoaded(currentURL_Home, currentTitle_Home, profileNavItem);
    }


}
