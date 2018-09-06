import org.testng.Assert;
import org.testng.annotations.*;

public class LinkedinSearchTest extends LinkedinBaseTest {


    @DataProvider
    public Object[][] search_HomePage() {
        return new Object[][]{
                {"autotestqa2018@gmail.com", "trust2018", "hr", 10}
        };
    }


    @Test(dataProvider = "search_HomePage")
    public void searchTestHomePage(String userEmail, String userPassword, String requestData, int resCount){
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(driver, driverWait);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded");

        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);
        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded");
        Assert.assertTrue(linkedinHomePage.isProfileNavItemDisplayed(), "profileNavItem button is not displayed on Home page");

        Assert.assertTrue(linkedinHomePage.isSearchFieldDisplayed(), "SearchField is not displayed");
        linkedinHomePage.makeSearchRequest(requestData);
        Assert.assertTrue(linkedinHomePage.isSearchContainerDisplayed(), "SearchForm is not displayed");
        Assert.assertEquals(linkedinHomePage.allFiltersButtonGetName(), "All Filters", "Controll button AllFilters does not match");

        Assert.assertEquals(linkedinHomePage.quantityOfSearchFormAnswers(), resCount, "Quantity of answers on search form is wrong");
        Assert.assertEquals(linkedinHomePage.quantityOfMatchResults(requestData), resCount, "Not all the results coincided");

    }


}
