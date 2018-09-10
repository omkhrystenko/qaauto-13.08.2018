package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinHomePage extends LinkedinBasePage {
    @FindBy(xpath = "//li[@id = 'profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "//input[@placeholder and @role = 'combobox]'")
    private WebElement searchField;

    @FindBy(xpath = "//*[@class=\"search-results-container\"]")
    private WebElement searchContainer;

    @FindBy(xpath = "//*[@class=\"search-filters-bar__filter-grouping display-flex align-items-center \"]/button")
    private WebElement allFilterButton;

    @FindBy(xpath = "//*[@class=\"search-results-container\"]/descendant:: div[@class = \"search-results-page core-rail\"]/div/div/ul/li")
    private List<WebElement> searchResultContainers;






    public LinkedinHomePage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(driver, this);
    }

    public boolean isProfileNavItemDisplayed(){
        boolean res = false;
        if(profileNavItem.isDisplayed()){
            res = true;
        }
        return res;
    }

    public boolean isPageLoaded(){
        String currentURL_Home = "https://www.linkedin.com/feed/";
        String currentTitle_Home = "LinkedIn";
        return isPageLoaded(currentURL_Home, currentTitle_Home, profileNavItem);
    }


    public boolean isSearchFieldDisplayed() {
        return searchField.isDisplayed();
    }

    public void makeSearchRequest(String requestData) {
        searchField.click();
        searchField.sendKeys(requestData + Keys.ENTER);
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSearchContainerDisplayed() {
        return searchContainer.isDisplayed();
    }

    public String allFiltersButtonGetName() {
        String res = "";
        if(allFilterButton.isDisplayed()){
            res = allFilterButton.getText().trim();
        }

        return res;
    }

    public int quantityOfSearchFormAnswers() {



        int res = 0;

        for(int i = 0; i < searchResultContainers.size(); i++){
            WebElement container = searchResultContainers.get(i);
           scrollToElement(container, driver);

            String conteinerContent = container.getAttribute("innerHTML");
            if(conteinerContent.contains("class=\"results-list display-flex pb3 ember-view\"")){
                continue;
            }else  res++;
        }

        return res;
    }

    public int quantityOfMatchResults(String compareText) {
        int res = 0;

        for(int i = 0; i < searchResultContainers.size(); i++){
            WebElement container = searchResultContainers.get(i);
            scrollToElement( container, driver);
            String conteinerContent = container.getAttribute("innerHTML");
            if(conteinerContent.contains("class=\"results-list display-flex pb3 ember-view\"")){
                continue;
            }else{
                if(compareContainerText(container, compareText)) res++;
            }
        }

        return res;
    }

    private boolean compareContainerText(WebElement container, String compareText){
        boolean res = false;
        String containerText = container.getText();
        if(containerText.contains(compareText) || containerText.contains(compareText.toUpperCase()) ||
                containerText.contains(compareText.toLowerCase())){
            res = true;
        }

        return res;

    }

    public void scrollToElement( WebElement element, WebDriver driver) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", element);
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
//////////////ДЗ
    public LinkedinSearchPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinSearchPage(driver);
    }
}
