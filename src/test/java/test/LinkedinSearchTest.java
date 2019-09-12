package test;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinSearchPage;

import java.util.List;
/**
 * LinkedinSearchTest Object class.
 */

@Feature(value = "Search")
@Story(value = "Item search")
public class LinkedinSearchTest extends LinkedinBaseTest {


    /**
     * Verify the displaying of the search result
     *
     * Preconditions
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario
     * - Open login page
     * - Verify login page is loaded
     * - Login with valid credentials
     * - Verify home page is loaded
     * - Search for 'hr' Searchterm
     * - Verify Search page is loaded
     * - Verify 10 results displayed on search page
     * - Verify each result item contains searchTerm
     */
    @Test
    public void basicSearchTest() {
        String userEmail = "linkedin.tst.yanina@gmail.com";
        String userPassword = "Test123!";
        String searchTerm = "HR";


        Assert.assertTrue(linkedinLoginPage.isPageLoaded(), "Login page is not loaded.");
        LinkedinHomePage linkedinHomePage = linkedinLoginPage.login(userEmail, userPassword);

        Assert.assertTrue(linkedinHomePage.isPageLoaded(), "Home page is not loaded.");

        LinkedinSearchPage linkedinSearchPage = linkedinHomePage.search(searchTerm);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(), "Search page is not loaded.");

        Assert.assertEquals(linkedinSearchPage.getSearchResultsNumber(), 10,
                "Wrong number of searchResults on Search page.");

        List<String> searchResultsList = linkedinSearchPage.getSearchResultsList();

        for (String searchResult : searchResultsList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm "+searchTerm+" not found in:\n"+searchResult);
        }
    }

}
