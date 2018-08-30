import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage extends BasePage {

    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInButton;

    public LinkedinLoginPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
        initElements();
    }

    private void initElements(){
        userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));
    }


    public void login(String userEmail, String userPassword){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public void sendKeysToLoginField(String text){
        userEmailField.sendKeys(text);
    }

    public void sendKeysToPasswordField(String text){
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
        return isPageLoaded(currentURL_Login, currentTitle_Login);
    }



}
