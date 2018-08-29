import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePersonPage extends BasePage{
    WebElement profileNavItem;

    public ProfilePersonPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
        initElements();
    }

    private void initElements(){
        profileNavItem = driver.findElement(By.xpath("//li[@id = 'profile-nav-item']"));
    }

    public boolean isProfileNavItemDisplayed(){
        boolean res = false;
        if(profileNavItem.isDisplayed()){
            res = true;
        }
        return res;
    }


}
