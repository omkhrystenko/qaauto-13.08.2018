import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinSubmitLoginPage extends BasePage {

    @FindBy(xpath = "//div[@role = 'alert']")
    private WebElement alertMessage;
    @FindBy(xpath = "//*[@id=\"session_key-login-error\"]")
    private WebElement errorAnnotationLogin;
    @FindBy(xpath = "//*[@id=\"session_password-login-error\"]")
    private WebElement errorAnnotationPassword;



    public LinkedinSubmitLoginPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
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
        String loginAnnText = errorAnnotationLogin.getText();
        String passwAnnText = errorAnnotationPassword.getText();
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


}
