package page_object.pages.google;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage {
    private final WebDriver driver;

    public GooglePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    /**
     * определение локатора поля ввода поискового значения
     */
    @FindBy(css = "input[name=\"q\"]")
    private WebElement searchInput;
    /**
     * определение локатора логотипа Google
     */
    @FindBy(css = "img.lnXdpd")
    private WebElement logo;
    /**
     * метод который возвращает значение атрибута alt у logo
     */
    public String getAltLogoAttr() {
        return logo.getAttribute("alt");
    }
    /**
     * метод который сабмитит поиск после заполнения поля
     */
    public void fillTextAndFind(String text) {
        searchInput.clear();
        searchInput.sendKeys(text + Keys.ENTER);
    }
}
