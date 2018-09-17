package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * LinkedinRequestPasswordResetPage Object class.
 */
public class LinkedinRequestPasswordResetPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    /**
     * Constructor for LinkedinRequestPasswordResetPage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinRequestPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(findAccountButton, 10);
    }

    /**
     * Check is page loaded up on to controll parameters.
     *
     * @return result of loading.
     */
    public boolean isLoaded() {
        return findAccountButton.isDisplayed()
                && getCurrentTitle().equals("Reset Password | LinkedIn")
                && getCurrentUrl().contains("uas/request-password-reset");
    }

    /**
     * Sending email acc to receive email link for password changing.
     *
     * @param userEmail - User email.
     * @return page that follows.
     */
    public LinkedinPasswordResetSubmitPage findAccount(String userEmail) {
        gMailService.connect();

        userEmailField.sendKeys(userEmail);
        findAccountButton.click();

        return new LinkedinPasswordResetSubmitPage(driver);
    }
}

