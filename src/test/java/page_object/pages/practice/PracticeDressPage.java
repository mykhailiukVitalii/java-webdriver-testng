package page_object.pages.practice;

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
    private WebDriverWait wait;

    public PracticeDressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        //TODO: use no hurdcode value for the timeout: 10
        this.wait = new WebDriverWait(driver, 10);
    }
    //Open PracticeDressPage page
    public PracticeDressPage openSummerDressesPage(String url) {
        driver.get(url);

        return this;
    }

    /**
     * определение локатора dressesResults
     */
    @FindBy(css = "ul.product_list .product-container")
    private List<WebElement> dressesList;

    /**
     * метод возвращающий колличество элементов product на странице
     */
    public int productList() {
        //Wait product to be displayed.
        wait.until(ExpectedConditions.visibilityOf(dressesList.get(0)));

        return dressesList.size();
    }
}

//    Click Women
//    Добавить каждый второй товар(сохранить наименования)
//    Переходим в корзину
//        Проверяем товары в корзине