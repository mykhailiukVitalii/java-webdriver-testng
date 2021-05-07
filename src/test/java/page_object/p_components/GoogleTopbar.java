package page_object.p_components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleTopbar {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public GoogleTopbar(WebDriver driver) {
        PageFactory.initElements(driver, this);//TODO: what is it? рефактор - не прокидовать в каждом шаге
        this.driver = driver;
    }
    /**
     * определение локатора image TAB(rus)
     */
    @FindBy(xpath = "//a[text() = 'Картинки']") //TODO не нравится!!!!прокинуть название
    private WebElement imageTab;
    /**
     * определение локатора all TAB(rus)
     */
    @FindBy(xpath = "//a[text() = 'Все']") //TODO не нравится!!!!прокинуть название
    private WebElement allTab;
    /**
     * метод который переключается на IMG Табу
     */
    public void activeImgTab() {
        //explicity wait
        new WebDriverWait(driver, 5) //TODO: fix static timeout
                .until(ExpectedConditions.elementToBeClickable(imageTab)).click();
    }
    /**
     * метод который переключается на All Табу
     */
    public void activeAllTab() {
        new WebDriverWait(driver, 5) //TODO: fix static timeout
                .until(ExpectedConditions.elementToBeClickable(allTab)).click();
    }
}
