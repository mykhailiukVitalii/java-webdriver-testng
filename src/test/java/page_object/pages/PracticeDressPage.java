package page_object.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.misc.ConfProperties;

import java.util.List;

public class PracticeDressPage {
    private final WebDriver driver;

    public PracticeDressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    //todo: description
    public void openSummerDressesPage() {
        // Open Google home page
        this.driver.get(ConfProperties.getProperty("summerDressPracticepage"));
    }

    /**
     * определение локатора dressesResults
     */
    @FindBy(css = "ul.product_list .product-container")
    private List<WebElement> dressesList;

    /**
     * метод возвращающий колличество элементов product на странице
     */
    public int productList(int timeout) {
        //Wait produst to be displayed.
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOf(dressesList.get(0)));

        return dressesList.size();
    }
}
