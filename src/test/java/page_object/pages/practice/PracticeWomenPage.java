package page_object.pages.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PracticeWomenPage {
    private final WebDriver driver;
    private WebDriverWait wait;
    Actions actions;

    public PracticeWomenPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        //TODO: use no hurdcode value for the timeout: 10
        this.wait = new WebDriverWait(driver, 10);
        // Create an object of actions class and pass reference of WebDriver as a parameter to its constructor.
        actions = new Actions(driver); //TODO: не вішло заховерить продукт!!!что могло пойти не так????
    }
    /**
     * определение локатора itemsList
     */
    @FindBy(css = "ul.product_list .product-container")
    private List<WebElement> itemsList;

    /**
     * определение локатора с именем продукта
     */
    @FindBy(css = ".product-container a.product-name")
    private List<WebElement> itemText;
    /**
     * метод возвращающий колличество элементов product на странице
     */
    public int itemsListCount() {
        //Wait product to be displayed.
        wait.until(ExpectedConditions.visibilityOf(itemsList.get(0)));

        return itemsList.size();
    }
    /**
     * метод возвращающий колличество элементов product на странице
     */
    public PracticeWomenPage waitFirstExistItem(int index) {
        //Wait product to be displayed.
        wait.until(ExpectedConditions.visibilityOf(itemsList.get(index)));

        return this;
    }
    /**
     * метод возвращающий current item
     */
    public WebElement currentItem(int index) {
        return itemsList.get(index);
    }
    /**
     * метод открывающий страницу продукта
     */
    public PracticeWomenPage openItemPage(int index) {
        //Wait cross element and close form.
        wait.until(ExpectedConditions.elementToBeClickable(itemText.get(index))).click();

        return this;
    }
//    /**
//     * метод закрівающий форму
//     */
//    //TODO move to separate file as Form????проговорить вопрос голосом
//    public PracticeWomenPage closeForm() {
//        //Wait cross element and close form.
//        wait.until(ExpectedConditions.elementToBeClickable(closeForm));
//
//        return this;
//    }
}