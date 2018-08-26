import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {





    @Test
    public void successfulLoginTest() {
        //Navigate to 'Linkedin.com'
        //Verify that login page is loaded
        //Enter user e-mail
        //Enter user password
        //Click 'Sign in' button
        //Verify Home page is loaded

        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("autotestqa2018@gmail.com");
        userPasswordField.sendKeys("trust2018");
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Home page URL is wrong");
        Assert.assertEquals(driver.getTitle(), "LinkedIn", "Home page Title is wrong");

        WebElement profileNavItem = driver.findElement(By.xpath("//li[@id = 'profile-nav-item']"));
        Assert.assertTrue(profileNavItem.isDisplayed(), "profileNavItem button is not displayed on Home page");

        driver.quit();
    }

    @Test
    public void negativeloginTest(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("a@b.c");
        userPasswordField.sendKeys("wrong");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong");

        driver.quit();
    }


    @Test
    public void negativeloginTestEmptyFields(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        Assert.assertTrue(userEmailField.isDisplayed(), "user email field is not displayed on login page");
        Assert.assertTrue(userPasswordField.isDisplayed(), "user password field is not displayed on login page");
        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        driver.quit();
    }

    @Test
    public void negativeloginTestFieldsFilledWithBackspace(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("                   ");
        userPasswordField.sendKeys("       ");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        Assert.assertTrue(userEmailField.isDisplayed(), "user email field is not displayed on login page");
        Assert.assertTrue(userPasswordField.isDisplayed(), "user password field is not displayed on login page");
        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        driver.quit();
    }


    @Test
    public void negativeloginTestEmptyLoginRightPassword(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");


        userPasswordField.sendKeys("123456");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        Assert.assertTrue(userEmailField.isDisplayed(), "user email field is not displayed on login page");
        Assert.assertTrue(userPasswordField.isDisplayed(), "user password field is not displayed on login page");
        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        driver.quit();
    }

    @Test
    public void negativeloginTestRightLoginEmptyPassword(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("autotestqa2018@gmail.com");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        Assert.assertTrue(userEmailField.isDisplayed(), "user email field is not displayed on login page");
        Assert.assertTrue(userPasswordField.isDisplayed(), "user password field is not displayed on login page");
        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        driver.quit();
    }

    @Test
    public void negativeloginTestLoginIsPassword_PasswordIsLogin(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("trust2018");
        userPasswordField.sendKeys("autotestqa2018@gmail.com");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String pageLoginPasswordSubmit = "https://www.linkedin.com/uas/login-submit";
        Assert.assertEquals(driver.getCurrentUrl(), pageLoginPasswordSubmit, "Submit login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Submit login page title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong");

        //WebElement submitLoginPasswordWindow = driver.findElement(By.xpath("//*[@id=\"control_gen_2\"]"));
        //Assert.assertTrue(submitLoginPasswordWindow.isDisplayed(), "Submit login_password window is absent");

        WebElement errorAnnotationLogin = driver.findElement(By.xpath("//*[@id=\"session_key-login-error\"]"));
        Assert.assertEquals(errorAnnotationLogin.getText(), "Please enter a valid email address.", "Login error annotation is wrong");

        WebElement errorAnnotationPassword = driver.findElement(By.xpath("//*[@id=\"session_password-login-error\"]"));
        Assert.assertEquals(errorAnnotationPassword.getText(), "", "Password error annotation is wrong");

        driver.quit();
    }

    @Test
    public void negativeloginTestLoginIsRight_PasswordIsLess4(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("autotestqa2018@gmail.com");
        userPasswordField.sendKeys("12345");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String pageLoginPasswordSubmit = "https://www.linkedin.com/uas/login-submit";
        Assert.assertEquals(driver.getCurrentUrl(), pageLoginPasswordSubmit, "Submit login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Submit login page title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong");

        //WebElement submitLoginPasswordWindow = driver.findElement(By.xpath("//*[@id=\"control_gen_2\"]"));
        //Assert.assertTrue(submitLoginPasswordWindow.isDisplayed(), "Submit login_password window is absent");

        WebElement errorAnnotationLogin = driver.findElement(By.xpath("//*[@id=\"session_key-login-error\"]"));
        Assert.assertEquals(errorAnnotationLogin.getText(), "", "Login error annotation is wrong");

        WebElement errorAnnotationPassword = driver.findElement(By.xpath("//*[@id=\"session_password-login-error\"]"));
        Assert.assertEquals(errorAnnotationPassword.getText(), "The password you provided must have at least 6 characters.", "Password error annotation is wrong");

        driver.quit();
    }

    @Test
    public void negativeloginTestLoginIsRight_PasswordIsWrong(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("autotestqa2018@gmail.com");
        userPasswordField.sendKeys("123456");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String pageLoginPasswordSubmit = "https://www.linkedin.com/uas/login-submit";
        Assert.assertEquals(driver.getCurrentUrl(), pageLoginPasswordSubmit, "Submit login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Submit login page title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong");

        //WebElement submitLoginPasswordWindow = driver.findElement(By.xpath("//*[@id=\"control_gen_2\"]"));
        //Assert.assertTrue(submitLoginPasswordWindow.isDisplayed(), "Submit login_password window is absent");

        WebElement errorAnnotationLogin = driver.findElement(By.xpath("//*[@id=\"session_key-login-error\"]"));
        Assert.assertEquals(errorAnnotationLogin.getText(), "", "Login error annotation is wrong");

        WebElement errorAnnotationPassword = driver.findElement(By.xpath("//*[@id=\"session_password-login-error\"]"));
        Assert.assertEquals(errorAnnotationPassword.getText(), "Hmm, that's not the right password. Please try again or request a new one.", "Password error annotation is wrong");

        driver.quit();
    }

    @Test
    public void negativeloginTestLoginWithBsEmlEnding_PasswordIsRight(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("            @gmail.com");
        userPasswordField.sendKeys("123456");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String pageLoginPasswordSubmit = "https://www.linkedin.com/uas/login-submit";
        Assert.assertEquals(driver.getCurrentUrl(), pageLoginPasswordSubmit, "Submit login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Submit login page title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong");

        //WebElement submitLoginPasswordWindow = driver.findElement(By.xpath("//*[@id=\"control_gen_2\"]"));
        //Assert.assertTrue(submitLoginPasswordWindow.isDisplayed(), "Submit login_password window is absent");

        WebElement errorAnnotationLogin = driver.findElement(By.xpath("//*[@id=\"session_key-login-error\"]"));
        Assert.assertEquals(errorAnnotationLogin.getText(), "Please enter a valid email address.", "Login error annotation is wrong");

        WebElement errorAnnotationPassword = driver.findElement(By.xpath("//*[@id=\"session_password-login-error\"]"));
        Assert.assertEquals(errorAnnotationPassword.getText(), "", "Password error annotation is wrong");

        driver.quit();
    }


    @Test
    public void negativeloginTestLoginIsScript_PasswordIsRight(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        String mainURL = "https://www.linkedin.com/";

        driver.get(mainURL);

        Assert.assertEquals(driver.getCurrentUrl(), mainURL, "Login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up", "Login page title is wrong");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id = 'login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id = 'login-password']"));
        WebElement signInButton = driver.findElement(By.xpath("//input[@id = 'login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(), "sign in button is not displayed on login page");

        userEmailField.sendKeys("<script>alert(123)</script>");
        userPasswordField.sendKeys("123456");
        signInButton.click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String pageLoginPasswordSubmit = "https://www.linkedin.com/uas/login-submit";
        Assert.assertEquals(driver.getCurrentUrl(), pageLoginPasswordSubmit, "Submit login page URL doesn't not match");
        Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn", "Submit login page title is wrong");

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role = 'alert']"));
        Assert.assertEquals(alertMessage.getText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong");

        //WebElement submitLoginPasswordWindow = driver.findElement(By.xpath("//*[@id=\"control_gen_2\"]"));
        //Assert.assertTrue(submitLoginPasswordWindow.isDisplayed(), "Submit login_password window is absent");

        WebElement errorAnnotationLogin = driver.findElement(By.xpath("//*[@id=\"session_key-login-error\"]"));
        Assert.assertEquals(errorAnnotationLogin.getText(), "Please enter a valid email address.", "Login error annotation is wrong");

        WebElement errorAnnotationPassword = driver.findElement(By.xpath("//*[@id=\"session_password-login-error\"]"));
        Assert.assertEquals(errorAnnotationPassword.getText(), "", "Password error annotation is wrong");

        driver.quit();
    }



}






