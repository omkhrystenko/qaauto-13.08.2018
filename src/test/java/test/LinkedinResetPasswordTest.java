package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class LinkedinResetPasswordTest extends LinkedinBaseTest{

    @DataProvider
    public Object[][] changingPassword() {
        return new Object[][]{
                {"autotestqa2018@gmail.com",  "TORYV@1t11"}
        };
    }

    @Test(dataProvider = "changingPassword")
    public void resetPasswordTest(String userEmail, String newPassword){
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "The Login page is not loaded");
        LinkedinPasswordResetPage linkedinPasswordResetPage = linkedinLoginPage.clickForgotPasswordLink();
        Assert.assertTrue(linkedinPasswordResetPage.isPageLoaded(),"Password Reset Page is not loaded");
        linkedinPasswordResetPage.enterUserEmail(userEmail);
        LinkedinRequestPasswordResetSubmitPage linkedinRequestPasswordResetSubmitPage = linkedinPasswordResetPage.clickFindAccount();
        Assert.assertTrue(linkedinRequestPasswordResetSubmitPage.isPageLoaded(),"Request Password Reset Submit Page is not loaded");
        try {
            sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinkedinRetypePasswordPage linkedinRetypePasswordPage = new LinkedinRetypePasswordPage(driver);
        Assert.assertTrue(linkedinRetypePasswordPage.isPageLoaded(), "Retype Password Page is not loaded");
        linkedinRetypePasswordPage.enterNewPassword(newPassword);
        linkedinRetypePasswordPage.enterRetypeNewPassword(newPassword);
        linkedinRetypePasswordPage.checkRequireAllDevices();
        LinkedinPasswordResetSubmitPage linkedinPasswordResetSubmitPage = linkedinRetypePasswordPage.clickSubmitButton();
        Assert.assertTrue(linkedinPasswordResetSubmitPage.isPageLoaded(),"Password Reset Submit Page is not loaded");
        LinkedinHomePage linkedinHomePage = linkedinPasswordResetSubmitPage.clickGoHomepage();
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded");
    }
}