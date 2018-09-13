package page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinPasswordResetSubmitPage extends LinkedinBasePage {
    @FindBy(xpath = "//button[@id='resend-url']")
    private WebElement resendLinkButton;

    public LinkedinPasswordResetSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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

    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        System.out.println(gMailService.user);
        System.out.println(gMailService.pass);
        String messageSubject = "here's the link to reset your password";
        String messageTo = "autotestqa2018@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println(message);
        String overloadPassword = hanleGMailServiceResponce(message);
        System.out.println(overloadPassword);
        driver.get(overloadPassword);

        return new LinkedinSetNewPasswordPage(driver);
    }

    private String hanleGMailServiceResponce(String response){
        String res = "";
        StringBuilder stringBuilder = new StringBuilder(response);
        int linkNameStart = stringBuilder.indexOf("To change your LinkedIn password, click <a href=\"");
        int linkStart = stringBuilder.indexOf("https", linkNameStart);
        int linkEnd = stringBuilder.indexOf("\" style", linkStart);
        res = stringBuilder.substring(linkStart, linkEnd);
        res = res.replace("&amp;", "&");

        return res;
    }
}