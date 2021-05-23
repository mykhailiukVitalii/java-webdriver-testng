package page_object.pages.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeMyAccountPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public PracticeMyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //TODO: use no hurdcode value for the timeout: 10
        this.wait = new WebDriverWait(driver, 10);
    }
    /**
     * определение локатора WomenTab
     */
    @FindBy(css = "a[title=\"Women\"]")
    private WebElement womenTab;
    /**
     * определение локатора Women as bread crumbs
     */
    @FindBy(css = "span.navigation_page")
    private WebElement womenBread;

    //Click on the Women Tab
    public PracticeMyAccountPage openWomenTab() {
        //Wait Women TAB and click on it
        wait.until(ExpectedConditions.elementToBeClickable(womenTab)).click();
        wait.until(ExpectedConditions.visibilityOfAllElements(womenBread));

        return this;
    }
}
