package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 *  LinkedinSubmitPage Object class.
 */
public class LinkedinLoginSubmitPage extends LinkedinBasePage {

    @FindBy(xpath = "//div[@role = 'alert']")
    private WebElement alertMessage;
    @FindBy(xpath = "//*[@id=\"session_key-login-error\"]")
    private WebElement userEmailAlert;
    @FindBy(xpath = "//*[@id=\"session_password-login-error\"]")
    private WebElement userPasswordAlert;


    /**
     * Constructor for LinkedinHomePage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinLoginSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(alertMessage, 10);
    }

    /**
     * Check is page loaded up on to controll parameters.
     */
    public boolean isPageLoaded(){
        waitUntilElementVisible(alertMessage, 10);
        String currentURL_SubmitLogin = "https://www.linkedin.com/uas/login-submit";
        String currentTitle_SubmitLogin = "Sign In to LinkedIn";
        return isPageLoaded(currentURL_SubmitLogin, currentTitle_SubmitLogin, alertMessage);
    }

    /**
     * Get alert message content.
     *
     * @return alert message content.
     */
    public String getAlertMessageText(){
        return alertMessage.getText();
    }

    /**
     * Get user email alert text.
     *
     * @return email alert text.
     */
    public String getUserEmailAlertText(){
        return userEmailAlert.getText();
    }

    /**
     * Get user password alert text.
     *
     * @return password alert text.
     */
    public String getUserPasswordAlertText(){
        return userPasswordAlert.getText();
    }

}
