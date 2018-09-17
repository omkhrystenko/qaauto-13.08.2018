package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * LinkedinSetNewPasswordPage Object class.
 */
public class LinkedinSetNewPasswordPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitButton;

    /**
     * Constructor for LinkedinSetNewPasswordPage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinSetNewPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(newPasswordField, 10);
    }

    /**
     * Set new password.
     *
     * @param newUserPassword - new password.
     * @return - page that follows.
     */
    public LinkedinSuccessfulPasswordResetPage submitNewPassword(String newUserPassword) {
        newPasswordField.sendKeys(newUserPassword);
        confirmPasswordField.sendKeys(newUserPassword);
        submitButton.click();
        return new LinkedinSuccessfulPasswordResetPage(driver);
    }
    /**
     * Check is page loaded up on to controll parameters.
     *
     * @return result of loading.
     */
    public boolean isLoaded() {
        waitUntilElementVisible(newPasswordField, 10);
        return newPasswordField.isDisplayed();
    }
}