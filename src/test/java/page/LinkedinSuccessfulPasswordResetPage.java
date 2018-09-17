package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * LinkedinSuccessfulPasswordResetPage Object class.
 */
public class LinkedinSuccessfulPasswordResetPage extends LinkedinBasePage {
    @FindBy(xpath = "//button[text()='Go to homepage']")
    private WebElement goToHomepageButton;
    /**
     * Constructor for LinkedinSetNewPasswordPage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinSuccessfulPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(goToHomepageButton, 10);
    }

    /**
     * Transfer to Home page.
     *
     * @return Home page class.
     */
    public LinkedinHomePage clickOnGoToHomeButton() {
        goToHomepageButton.click();
        return new LinkedinHomePage(driver);
    }
    /**
     * Check is page loaded up on to controll parameters.
     *
     * @return result of loading.
     */
    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed();
    }
}
