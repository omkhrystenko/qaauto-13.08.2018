package test;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLoginPage;
import page.LinkedinLoginSubmitPage;

/**
 * LinkedinLoginTest Object class.
 */
@Feature(value = "Auth")
@Story(value = "Login")

public class LinkedinLoginTest extends LinkedinBaseTest {

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                //{"autotestqa2018@gmail.com", "trust2018"},
                {"AUTOtestqa2018@gmail.com", "trust2018"}
        };
    }

    @DataProvider
    public Object[][] wrongLoginPasswordSubmitPage() {
        return new Object[][]{
               /* {"a@b.c", "wrong", "Please enter a valid email address.", "The password you provided must have at least 6 characters."},
               /*{"trust2018", "autotestqa2018@gmail.com", "Please enter a valid email address.", ""},
                {"autotestqa2018@gmail.com", "12345", "", "The password you provided must have at least 6 characters."},
                {"autotestqa2018@gmail.com", "123456", "", "Hmm, that's not the right password. Please try again or request a new one."},
                {"            @gmail.com", "123456", "Please enter a valid email address.", ""},*/
                {"<script>alert(123)</script>", "123456", "Please enter a valid email address.", ""}
        };
    }

    @DataProvider
    public Object[][] wrongLoginPasswordLoginPage() {
        return new Object[][]{
                /*{"", ""},
                {"        ", "      "},
                {"", "123456"},*/
                {"autotestqa2018@gmail.com", ""}

        };
    }

    @DataProvider
    public Object[][] searchHomePage() {
        return new Object[][]{
                {"autotestqa2018@gmail.com", "trust2018"}
        };
    }


    /**
     * Verify successful user Login
     *
     * Preconditions
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario
     * - Verify that login page is loaded.
     * - Enter userEmail.
     * - Enter userPassword.
     * - Click on 'Sign in' button.
     * - Verify Home page is loaded.
     */
    //@Title(value l= "") должен менять название теста
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "validDataProvider")

    public void successfulLoginTest(String userEmail, String userPassword) {

        //LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver);
        //Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login pageOld is not loaded");

        //LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);
        //Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home pageOld is not loaded");
        //Assert.assertTrue(linkedinHomePage.isProfileNavItemDisplayed(), "profileNavItem button is not displayed on Home pageOld");

    }


    /**
     * Verify negative cases that switch to CheckSubmit page
     *
     * Preconditions
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario
     * - Verify that login page is loaded.
     * - Enter userEmail.
     * - Enter userPassword.
     * - Click on 'Sign in' button.
     * - Verify  CheckSubmit page is loaded.
     * - Verify Alert messages is right
     */
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "wrongLoginPasswordSubmitPage")
    public void negativeloginTestSubmitPage(String userEmail, String userPassword,
                                  String emeilErrorMess, String passwordErrorMess){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login pageOld is not loaded");

        LinkedinLoginSubmitPage linkedinSubmitLoginPage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinSubmitLoginPage.isPageLoaded(), "LoginSubmitPage is not loaded.");

        Assert.assertEquals(linkedinSubmitLoginPage.getAlertMessageText(), "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message is wrong");
        Assert.assertEquals(linkedinSubmitLoginPage.getUserEmailAlertText(), emeilErrorMess,
                "userEmail alert text is wrong");
        Assert.assertEquals(linkedinSubmitLoginPage.getUserPasswordAlertText(), passwordErrorMess,
                "userPassword alert text is wrong");
    }



    /**
     * Verify negative cases that doesn't switch to home page
     *
     * Preconditions
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario
     * - Verify that login page is loaded.
     * - Enter userEmail.
     * - Enter userPassword.
     * - Click on 'Sign in' button.
     * - Verify Home page is not loaded.
     */
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "wrongLoginPasswordLoginPage")
    public void negativeloginTestLoginPage(String login, String password){

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login pageOld is not loaded");

        Assert.assertTrue(linkedinLoginPage.signInButtonIsDisplayed(), "sign in button is not displayed on login pageOld");


        linkedinLoginPage.fillLoginPasswordFields(login, password);
        linkedinLoginPage.signInButtonClick();

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login pageOld URL or Title doesn't not match");

        Assert.assertTrue(linkedinLoginPage.isControlElementsDisplayed(), "Controll elements displaying failed");
    }
}






