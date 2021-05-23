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

public class PracticeCartPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public PracticeCartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        //TODO: use no hurdcode value for the timeout: 10
        this.wait = new WebDriverWait(driver, 10);
    }
    /**
     * ...
     */
    @FindBy(css = ".product-name > .cart_block_product_name")
    private List<WebElement> itemsListFromCart;
    /**
     * Cart page opening button
     */
    @FindBy(css = "a[title=\"View my shopping cart\"]")
    private WebElement myCartButton;
    /**
     * Cart title element
     */
    @FindBy(css = "#cart_title")
    private WebElement cartTitle;
    /**
     * ...
     */
    public PracticeCartPage openCart() {
        //click on the Cart button
        myCartButton.click();
        //wait Cart title
        wait.until(ExpectedConditions.visibilityOf(cartTitle));

        return this;
    }
    /**
     * метод возвращающий список имен товаров добавленных в Корзину
     */
    public List<String> nameFromCartList() {
        //...
        List<String> names = new ArrayList<String>();
        int i=0;
        try {
            Thread.sleep(1500);
            //TODO: можно ли так делать? в трае чекать?
            for(WebElement element:itemsListFromCart){
                names.add(element.getAttribute("title"));
                System.out.println("Section "+i+":"+element.getAttribute("title"));
                i++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return names;
    }
}
