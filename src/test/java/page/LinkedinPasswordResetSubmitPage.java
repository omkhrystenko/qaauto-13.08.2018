package page;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;
/**
 * LinkedinPasswordResetSubmitPage Object class.
 */
public class LinkedinPasswordResetSubmitPage extends LinkedinBasePage {
    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    /**
     * Constructor for LinkedinPasswordResetSubmitPage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinPasswordResetSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(resendLinkButton, 10);
    }
    /**
     * Check is page loaded up on to controll parameters.
     *
     * @return result of loading.
     */
    public boolean isLoaded() {
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resendLinkButton.isDisplayed()
                && getCurrentTitle().contains("Please check your mail for reset password link.")
                && getCurrentUrl().contains("request-password-reset-submit");
    }

    /**
     * Get and activate change password link from email.
     *
     * @return page that follows email link sending.
     */
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "autotestqa2018@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);
        String resetPasswordLink =
            StringUtils.substringBetween(message, "To change your LinkedIn password, click <a href=\"",
                "\" style").replace("&amp;", "");
        System.out.println(resetPasswordLink);
        driver.get(resetPasswordLink);

        return new LinkedinSetNewPasswordPage(driver);
    }

}