import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


public class LinkedinLoginTest {

    WebDriver driver;
    WebDriverWait driverWait;
    String mainURL;

    @BeforeClass
    public void setSystemProps(){
        System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        mainURL = "https://www.linkedin.com/";
        driver.get(mainURL);
        driverWait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                {"autotestqa2018@gmail.com", "trust2018"},
                {"AUTOtestqa2018@gmail.com", "trust2018"}
        };
    }

    @DataProvider
    public Object[][] wrongLoginPassword_SubmitPage() {
        return new Object[][]{
                {"a@b.c", "wrong", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
                {"trust2018", "autotestqa2018@gmail.com", "Please enter a valid email address.", ""},
                {"autotestqa2018@gmail.com", "12345", "", "The password you provided must have at least 6 characters."},
                {"autotestqa2018@gmail.com", "123456", "", "Hmm, that's not the right password. Please try again or request a new one."},
                {"            @gmail.com", "123456", "Please enter a valid email address.", ""},
                {"<script>alert(123)</script>", "123456", "Please enter a valid email address.", ""}
        };
    }

    @DataProvider
    public Object[][] wrongLoginPassword_LoginPage() {
        return new Object[][]{
                {"", ""},
                {"        ", "      "},
                {"", "123456"},
                {"autotestqa2018@gmail.com", ""},

        };
    }


    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        //Navigate to 'Linkedin.com'
        //Verify that login page is loaded
        //Enter user e-mail
        //Enter user password
        //Click 'Sign in' button
        //Verify Home page is loaded


        //

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");

        linkedinLoginPage.login(userEmail, userPassword);

        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(driver, driverWait);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded");
        Assert.assertTrue(linkedinHomePage.isProfileNavItemDisplayed(), "profileNavItem button is not displayed on Home page");

    }

    @Test(dataProvider = "wrongLoginPassword_SubmitPage")
    public void negativeloginTestSubmitPage(String userEmail, String userPassword,
                                  String emeilErrorMess, String passwordErrorMess){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");

        linkedinLoginPage.login(userEmail, userPassword);
        LinkedinSubmitLoginPage linkedinSubmitLoginPage = new LinkedinSubmitLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinSubmitLoginPage.isPageLoaded(), "SubmitLogin page is not loaded");
        Assert.assertTrue(linkedinSubmitLoginPage.isAlertMessageDisplayed(),"Alert message text is wrong or is not displayed");
        Assert.assertTrue(linkedinSubmitLoginPage.isAlertAnnotationsTextMatches(emeilErrorMess, passwordErrorMess), "Login or Password error annotation is wrong");
    }


    @Test(dataProvider = "wrongLoginPassword_LoginPage")
    public void negativeloginTestLoginPage(String login, String password){

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");

        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.fillLoginPasswordFields(login, password);
        linkedinLoginPage.signInButtonClick();

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page URL or Title doesn't not match");
        Assert.assertTrue(linkedinLoginPage.isControlElementsDisplayed(), "Controll elements displaying failed");

    }

    /*@Test
    public void negativeloginTestFieldsFilledWithBackspace(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToLoginField("              ");
        linkedinLoginPage.sendKeysToPasswordField("          ");
        linkedinLoginPage.signInButtonClick();

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page URL or Title doesn't not match");
        Assert.assertTrue(linkedinLoginPage.isControlElementsDisplayed(), "Controll elements displaying failed");
    }*/


    /*@Test
    public void negativeloginTestEmptyLoginRightPassword(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToPasswordField("123456");
        linkedinLoginPage.signInButtonClick();

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page URL or Title doesn't not match");
        Assert.assertTrue(linkedinLoginPage.isControlElementsDisplayed(), "Controll elements displaying failed");

    }

    @Test
    public void negativeloginTestRightLoginEmptyPassword(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToLoginField("autotestqa2018@gmail.com");
        linkedinLoginPage.signInButtonClick();

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page URL or Title doesn't not match");
        Assert.assertTrue(linkedinLoginPage.isControlElementsDisplayed(), "Controll elements displaying failed");
    }

    @Test
    public void negativeloginTestLoginIsPassword_PasswordIsLogin(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToLoginField("trust2018");
        linkedinLoginPage.sendKeysToPasswordField("autotestqa2018@gmail.com");
        linkedinLoginPage.signInButtonClick();

        LinkedinSubmitLoginPage linkedinSubmitLoginPage = new LinkedinSubmitLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinSubmitLoginPage.isPageLoaded(), "SubmitLogin page is not loaded");
        Assert.assertTrue(linkedinSubmitLoginPage.isAlertMessageDisplayed(), "Alert message text is wrong or is not displayed");

        String errorAnnotTextLogin = "Please enter a valid email address.";
        String errorAnnotTextPassword = "";

        Assert.assertTrue(linkedinSubmitLoginPage.isAlertAnnotationsTextMatches(errorAnnotTextLogin,errorAnnotTextPassword), "Login or Password error annotation is wrong");
    }

    @Test
    public void negativeloginTestLoginIsRight_PasswordIsLess4(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToLoginField("autotestqa2018@gmail.com");
        linkedinLoginPage.sendKeysToPasswordField("12345");
        linkedinLoginPage.signInButtonClick();

        LinkedinSubmitLoginPage linkedinSubmitLoginPage = new LinkedinSubmitLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinSubmitLoginPage.isPageLoaded(), "SubmitLogin page is not loaded");
        Assert.assertTrue(linkedinSubmitLoginPage.isAlertMessageDisplayed("There were one or more errors in your submission. Please correct the marked fields below."), "Alert message text is wrong or is not displayed");

        String errorAnnotTextLogin = "";
        String errorAnnotTextPassword = "The password you provided must have at least 6 characters.";

        Assert.assertTrue(linkedinSubmitLoginPage.isAlertAnnotationsTextMatches(errorAnnotTextLogin,errorAnnotTextPassword), "Login or Password error annotation is wrong");
    }

    @Test
    public void negativeloginTestLoginIsRight_PasswordIsWrong(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToLoginField("autotestqa2018@gmail.com");
        linkedinLoginPage.sendKeysToPasswordField("123456");
        linkedinLoginPage.signInButtonClick();

        LinkedinSubmitLoginPage linkedinSubmitLoginPage = new LinkedinSubmitLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinSubmitLoginPage.isPageLoaded(), "SubmitLogin page is not loaded");
        Assert.assertTrue(linkedinSubmitLoginPage.isAlertMessageDisplayed("There were one or more errors in your submission. Please correct the marked fields below."), "Alert message text is wrong or is not displayed");

        String errorAnnotTextLogin = "";
        String errorAnnotTextPassword = "Hmm, that's not the right password. Please try again or request a new one.";

        Assert.assertTrue(linkedinSubmitLoginPage.isAlertAnnotationsTextMatches(errorAnnotTextLogin,errorAnnotTextPassword), "Login or Password error annotation is wrong");

    }

    @Test
    public void negativeloginTestLoginWithBsEmlEnding_PasswordIsRight(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToLoginField("            @gmail.com");
        linkedinLoginPage.sendKeysToPasswordField("123456");
        linkedinLoginPage.signInButtonClick();

        LinkedinSubmitLoginPage linkedinSubmitLoginPage = new LinkedinSubmitLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinSubmitLoginPage.isPageLoaded(), "SubmitLogin page is not loaded");
        Assert.assertTrue(linkedinSubmitLoginPage.isAlertMessageDisplayed("There were one or more errors in your submission. Please correct the marked fields below."), "Alert message text is wrong or is not displayed");

        String errorAnnotTextLogin = "Please enter a valid email address.";
        String errorAnnotTextPassword = "";

        Assert.assertTrue(linkedinSubmitLoginPage.isAlertAnnotationsTextMatches(errorAnnotTextLogin,errorAnnotTextPassword), "Login or Password error annotation is wrong");

    }*/


   /* @Test
    public void negativeloginTestLoginIsScript_PasswordIsRight(){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");
        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login page");

        linkedinLoginPage.sendKeysToLoginField("<script>alert(123)</script>");
        linkedinLoginPage.sendKeysToPasswordField("123456");
        linkedinLoginPage.signInButtonClick();

        LinkedinSubmitLoginPage linkedinSubmitLoginPage = new LinkedinSubmitLoginPage(driver, driverWait);

        Assert.assertTrue(linkedinSubmitLoginPage.isPageLoaded(), "SubmitLogin page is not loaded");
        Assert.assertTrue(linkedinSubmitLoginPage.isAlertMessageDisplayed("There were one or more errors in your submission. Please correct the marked fields below."), "Alert message text is wrong or is not displayed");

        String errorAnnotTextLogin = "Please enter a valid email address.";
        String errorAnnotTextPassword = "";

        Assert.assertTrue(linkedinSubmitLoginPage.isAlertAnnotationsTextMatches(errorAnnotTextLogin,errorAnnotTextPassword), "Login or Password error annotation is wrong");

    }*/
}






