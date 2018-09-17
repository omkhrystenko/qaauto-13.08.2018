package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import static java.lang.Thread.sleep;

/**
 * LinkedinLoginPage Object class.
 */
public class LinkedinLoginPage extends LinkedinBasePage {

    @FindBy(xpath = "//input[@id = 'login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@id = 'login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@id = 'login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;

    /**
     * Constructor for LinkedinLoginPage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); //Можем вычитать из другого класса тогда вместо this ставим pageOld.LinkedinHomePage.class
        waitUntilElementVisible(signInButton, 10);
    }


    /**
     * User login by username/password.
     *
     * @param userEmail - String with userEmail.
     * @param userPassword - String userPassword.
     * @param <T> - generic type to return different PegeObjects.
     * @return one of corresponding PageObjects LinkedinLoginPage/LinkedinHomePage/LinkedinLoginSubmitPage
     */
    public <T> T login(String userEmail, String userPassword){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();

        if(getCurrentUrl().contains("/feed")){
            return (T)new LinkedinHomePage(driver);
        }
        if(getCurrentUrl().contains("/login-submit")){
            return (T)new LinkedinLoginSubmitPage(driver);
        }else {
            return (T)PageFactory.initElements(driver, LinkedinLoginPage.class); //или Т()this; или(T)PageFactory.initElements(driver, pageOld.LinkedinLoginPage.class); - эта реализация вернет new pageOld.LinkedinLoginPage() с проинициализированными полями веб елементов
        }

    }

    /**
     * Verify is controll element displayed on the Login page.
     *
     * @return result of verifying.
     */
    public boolean signInButtonIsDisplayed(){
        return signInButton.isDisplayed();
    }


    /**
     *User login/password submition.
     */
    public void signInButtonClick(){
            signInButton.click();
    }

    /**
     * User login/password input filling.
     *
     * @param login - User login.
     * @param password - User password.
     */
    public void fillLoginPasswordFields(String login, String password){
        sendKeysToLoginField(login);
        sendKeysToPasswordField(password);
    }

    /**
     * User login input filling.
     *
     * @param text - User login.
     */
    private void sendKeysToLoginField(String text){
        userEmailField.sendKeys(text);
    }

    /**
     * User password input filling.
     *
     * @param text - User password.
     */
    private void sendKeysToPasswordField(String text){
        userPasswordField.sendKeys(text);
    }


    /**
     * Verify controll fields is displayed.
     *
     * @return result of avaliability controll fields on the page.
     */
    public boolean isControlElementsDisplayed(){
        waitUntilElementVisible(signInButton, 10);
        return userEmailField.isDisplayed() &&
                userPasswordField.isDisplayed() &&
                signInButton.isDisplayed();
    }


    /**
     * Check is page loaded up on to controll parameters.
     *
     * @return result of loading.
     */
    public boolean isPageLoaded(){
        String currentURL_Login = "https://www.linkedin.com/";
        String currentTitle_Login = "LinkedIn: Log In or Sign Up";
        return isPageLoaded(currentURL_Login, currentTitle_Login, signInButton);
    }


    /**
     * Initiate sending controll email.
     *
     * @return  - generic type to return different PegeObjects.
     */
    public <T> T clickOnForgotPasswordLink(){
        forgotPasswordLink.click();
        if(getCurrentUrl().contains("/uas/request-password-reset"))
            return (T) new LinkedinRequestPasswordResetPage(driver);
        else return (T) this;
    }



}
