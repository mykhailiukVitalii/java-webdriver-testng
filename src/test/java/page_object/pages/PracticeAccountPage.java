package page_object.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.misc.ConfProperties;

public class PracticeAccountPage {
    private final WebDriver driver;
    private PracticPage practicPage;

    public PracticeAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.practicPage = new PracticPage(driver);
    }
    //TODO: to separate file as PracticeAccountPage.java
    /**
     * определение локатора для ввода е-меил
     */
    @FindBy(css = "#email")
    private WebElement email;
    /**
     * определение локатора для ввода pwd
     */
    @FindBy(css = "#passwd")
    private WebElement pwd;
    /**
     * определение локатора submit btn
     */
    @FindBy(css = "#SubmitLogin")
    private WebElement submitLogin;
    //todo: description
    public void openAccountPage() {
        // Open Google home page
        this.driver.get(ConfProperties.getProperty("accountPracticepage"));
    }
    /**
     * метод для SignUp пользователя
     */
    public String signUp(String email, String pwd, int timeout) {
        //Wait email input is displayed and input name
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOf(this.email)).sendKeys(email);
        //Wait pwd input is displayed and input pwd
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOf(this.pwd)).sendKeys(pwd);
//        TimeUnit.MINUTES.sleep(2); //TODO: how add browser.pause()?????
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.elementToBeClickable(this.submitLogin)).click();
        //Return user name after succesfully login
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.visibilityOf(practicPage.getUserInfo())).getText(); //TODO: возможно перестарался с авейтами???
    }
}
