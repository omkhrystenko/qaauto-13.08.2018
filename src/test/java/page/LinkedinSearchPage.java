package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
/**
 * LinkedinSearchPage Object class.
 */
public class LinkedinSearchPage extends LinkedinBasePage{
    @FindBy(xpath = "//h3[contains(@class, 'search-results_total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//li[contains {@class, search-result search-result__occluded-item]")
    private List<WebElement> searchResults;

    /**
     * Constructor for LinkedinSearchPage.
     *
     * @param driver - driver instance from test.
     */
    public LinkedinSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(searchResultsTotal, 10);
    }
    /**
     * Check is page loaded up on to controll parameters.
     *
     * @return result of loading.
     */
    public boolean isPageLoaded(){
        return getCurrentUrl().contains("search/results")
                && getCurrentTitle().contains("| Search | Linkedin")
                && searchResultsTotal.isDisplayed();
    }

    /**
     * Count results of searching showed on the page.
     *
     * @return count of results.
     */
    public int getSearchResultsNumber() {
        return searchResults.size();
    }

    /**Get list of webelements with search data containers.
     * @return list of webelements with search data containers.
     */
    public List<String> getSearchResultsList(){
        List<String> searchResultList = new ArrayList<String>();
        for(WebElement searchResult : searchResults){
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", searchResults);
            searchResultList.add(searchResult.getText());
        }
        return searchResultList;
    }
}
