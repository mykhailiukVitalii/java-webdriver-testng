package page_object.pages.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PracticeDetailPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public PracticeDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        //TODO: use no hurdcode value for the timeout: 10
        this.wait = new WebDriverWait(driver, 10);
    }
    /**
     * определение локатора для Success Message from form
     */
    @FindBy(css = ".layer_cart_product h2")
    private WebElement succesMessage;
    /**
     * определение локатора для item name from form
     */
    @FindBy(css = "#layer_cart_product_title.product-name")
    private WebElement itemName;
    /**
     * ...
     */
    @FindBy(css = "#add_to_cart > button span")
    private WebElement addToCartFromViewForm;
    /**
     * метод возвращающий Имя товара
     */
    public String productName() {
        //Get item text
        return itemName.getText();
    }
    //TODO: стоит ли такую форму доавлять как отдельній пейдж???
    /**
     * метод возвращающий true если сообщение об Удачном добавлении отобразилось
     */
    public String getSuccessMessage() {
        //Get success Message
        return wait.until(ExpectedConditions.visibilityOf(succesMessage)).getText();
    }
    /**
     * метод который добавляет item в орзину
     */
    public PracticeDetailPage clickOnAddToCart(int index) {
        //Wait Add to cart btn and click
        wait.until(ExpectedConditions.elementToBeClickable(addToCartFromViewForm)).click();

        return this;
    }
}
