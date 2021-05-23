package page_object.p_components.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleResults {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public GoogleResults(WebDriver driver) {
        PageFactory.initElements(driver, this);//TODO: рефактор - не прокидовать в каждом шаге
        this.driver = driver;
    }
    /**
     * определение локатора ссылок результатов поиска
     */
    @FindBy(css = ".yuRUbf > a cite")
    private List<WebElement> searchResults;
    /**
     * метод который возвращает ссылку(url) строки поиска по индексу
     */
    public String searchResultByIndex(Integer index) {
        String url = searchResults.get(index).getText();
        System.out.println("Url text:" + url);

        return url;
    }
}
