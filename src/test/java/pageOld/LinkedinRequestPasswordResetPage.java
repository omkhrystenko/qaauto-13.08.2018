package pageOld;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import static java.lang.Thread.sleep;

public class LinkedinRequestPasswordResetPage extends LinkedinBasePage {

    @FindBy(xpath="//header[@class='content__header' and contains(text(),\"First, let's find your account\")]")
    private WebElement contentHeaderText;

    @FindBy(xpath="//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath="//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public LinkedinRequestPasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/uas/request-password-reset?trk=uno-reg-guest-home-forgot-password")
                && getCurrentTitle().contains("Reset Password | LinkedIn")
                && contentHeaderText.isDisplayed();
    }

    public void enterUserEmail(String email){
        userEmailField.sendKeys(email+ Keys.TAB);
    }

    public <T> T findAccount(){
        GMailService gMailService = new GMailService();
        gMailService.connect();

        findAccountButton.click();

        String messageSubject = "here's the link to reset your password";
        String messageTo = "autotestqa2018@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";


        String message = gMailService.waitMessage(messageSubject,
                messageTo, messageFrom, 180);
        System.out.println("Content: " + message);








        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(getCurrentUrl().contains("/rp/request-password-reset-submit"))
        return (T) new LinkedinRequestPasswordResetSubmitPage(driver);
        else return (T) this;
    }
}