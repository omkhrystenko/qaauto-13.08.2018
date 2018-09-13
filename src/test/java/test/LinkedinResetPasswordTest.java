package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedinSetNewPasswordPage;
import page.LinkedinSuccessfulPasswordResetPage;
import page.LinkedinHomePage;
import page.LinkedinPasswordResetSubmitPage;
import page.LinkedinRequestPasswordResetPage;

import static java.lang.Thread.sleep;

public class LinkedinResetPasswordTest extends LinkedinBaseTest {

    @DataProvider
    public Object[][] changingPassword() {
        return new Object[][]{
                {"TORYV@1t12"}
        };
    }

    @Test(dataProvider = "changingPassword")
    public void successfulResetPasswordTest(String newPassword) throws InterruptedException {
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),
                "LoginPage is not loaded.");

        LinkedinRequestPasswordResetPage linkedinRequestPasswordResetPage =
                linkedinLoginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(linkedinRequestPasswordResetPage.isLoaded(),
                "RequestPasswordResetPage is not loaded.");

        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage =
                linkedinRequestPasswordResetPage.findAccount("autotestqa2018@gmail.com");


        //sleep(30000);
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isLoaded(),
                "PasswordResetSubmitPage is not loaded.");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage =
                linkedinPasswordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(),
                "SetNewPasswordPage is not loaded.");

        LinkedinSuccessfulPasswordResetPage linkedinSuccessfulPasswordResetPage =
                linkedinSetNewPasswordPage.submitNewPassword(newPassword);
        Assert.assertTrue(linkedinSuccessfulPasswordResetPage.isLoaded(),
                "SuccessfulPasswordResetPage is not loaded.");

        LinkedinHomePage linkedinHomePage =
                linkedinSuccessfulPasswordResetPage.clickOnGoToHomeButton();
        //sleep(180000);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(),
                "HomePage is not loaded.");


    }
}
