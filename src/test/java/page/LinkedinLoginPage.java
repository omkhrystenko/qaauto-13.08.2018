package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@id = 'login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id = 'login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id = 'login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    public LinkedinLoginPage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this); //Можем вычитать из другого класса тогда вместо this ставим page.LinkedinHomePage.class
    }


    public <T> T login(String userEmail, String userPassword){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(getCurrentUrl().contains("/feed")){
            return (T)new LinkedinHomePage(driver, driverWait);
        }
        if(getCurrentUrl().contains("/login-submit")){
            return (T)new LinkedinSubmitLoginPage(driver, driverWait);
        }else {
            return (T)PageFactory.initElements(driver, LinkedinLoginPage.class); //или Т()this; или(T)PageFactory.initElements(driver, page.LinkedinLoginPage.class); - эта реализация вернет new page.LinkedinLoginPage() с проинициализированными полями веб елементов
        }

    }

    public boolean signInButtonIsDisplayed(){
        return signInButton.isDisplayed();
    }


    public void signInButtonClick(){
            signInButton.click();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fillLoginPasswordFields(String login, String password){
        sendKeysToLoginField(login);
        sendKeysToPasswordField(password);
    }

    private void sendKeysToLoginField(String text){
        userEmailField.sendKeys(text);
    }

    private void sendKeysToPasswordField(String text){
        userPasswordField.sendKeys(text);
    }


    public boolean isControlElementsDisplayed(){
        return userEmailField.isDisplayed() &&
                userPasswordField.isDisplayed() &&
                signInButton.isDisplayed();
    }



    public boolean isPageLoaded(){
        String currentURL_Login = "https://www.linkedin.com/";
        String currentTitle_Login = "LinkedIn: Log In or Sign Up";
        return isPageLoaded(currentURL_Login, currentTitle_Login, signInButton);
    }

    public <T> T clickForgotPasswordLink(){
        forgotPasswordLink.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(getCurrentUrl().contains("/uas/request-password-reset"))
            return (T) new LinkedinPasswordResetPage(driver);
        else return (T) this;
    }



}
