import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinSubmitLoginPage extends LinkedinBasePage {

    @FindBy(xpath = "//div[@role = 'alert']")
    private WebElement alertMessage;
    @FindBy(xpath = "//*[@id=\"session_key-login-error\"]")
    private WebElement userEmailAlert;
    @FindBy(xpath = "//*[@id=\"session_password-login-error\"]")
    private WebElement userPasswordAlert;



    public LinkedinSubmitLoginPage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public boolean isAlertMessageDisplayed(){
        boolean res = false;
        String messText = "There were one or more errors in your submission. Please correct the marked fields below.";
        String messFromPage = alertMessage.getText();
        if(messFromPage.equals(messText)) res = true;
        return res;
    }

    public boolean isAlertAnnotationsTextMatches(String loginAnnotation, String passwordAnnotation){
        boolean res = false;
        String loginAnnText = userEmailAlert.getText();
        String passwAnnText = userPasswordAlert.getText();
        if(loginAnnotation.equals(loginAnnText) && passwAnnText.equals(passwordAnnotation)){
            res = true;
        }
        return res;
    }



    public boolean isPageLoaded(){
        String currentURL_SubmitLogin = "https://www.linkedin.com/uas/login-submit";
        String currentTitle_SubmitLogin = "Sign In to LinkedIn";
        return isPageLoaded(currentURL_SubmitLogin, currentTitle_SubmitLogin, alertMessage);
    }

    public String getAlertMessageText(){
        return alertMessage.getText();
    }

    public String getUserEmailAlertText(){
        return userEmailAlert.getText();
    }

    public String getUserPasswordAlertText(){
        return userPasswordAlert.getText();
    }

}
