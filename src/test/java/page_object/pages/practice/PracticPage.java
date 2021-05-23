package page_object.pages.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page_object.misc.ConfProperties;

public class PracticPage {
    private final WebDriver driver;

    public PracticPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    //todo: description
    public void openHomePage() {
        // Open Google home page
        driver.get(ConfProperties.getProperty("homePracticepage"));
    }
    //TODO: to separate file as PracticeMainPage.java
    /**
     * определение локатора userinfo
     */
    @FindBy(css = ".header_user_info > a >  span")
    private WebElement userinfo;

    public WebElement getUserInfo() {
        return userinfo;
    }
}
