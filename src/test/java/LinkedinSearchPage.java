import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage{
    @FindBy(xpath = "//h3[contains(@class, 'search-results_total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//li[contains {@class, search-result search-result__occluded-item]")
    private List<WebElement> searchResults;

    public LinkedinSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded(){
        return getCurrentUrl().contains("search/results")
                && getCurrentTitle().contains("| Search | Linkedin")
                && searchResultsTotal.isDisplayed();
    }

    public int getSearchResultsNumber() {
        return searchResults.size();
    }

    public List<String> getSearchResultList(){
        List<String> searchResultList = new ArrayList<String>();
        for(WebElement searchResult : searchResults){
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", searchResults);
            searchResultList.add(searchResult.getText());
        }
        return searchResultList;
    }
}
