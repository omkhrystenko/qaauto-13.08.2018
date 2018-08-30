import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinSubmitLoginPage extends BasePage {

    private WebElement alertMessage;
    private WebElement errorAnnotationLogin;
    private WebElement errorAnnotationPassword;

    public LinkedinSubmitLoginPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
        initElements();
    }

    private void initElements(){
        alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        errorAnnotationLogin = driver.findElement(By.xpath("//*[@id=\"session_key-login-error\"]"));
        errorAnnotationPassword = driver.findElement(By.xpath("//*[@id=\"session_password-login-error\"]"));

    }

    public boolean isAlertMessageDisplayed(String messText){
        boolean res = false;
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
