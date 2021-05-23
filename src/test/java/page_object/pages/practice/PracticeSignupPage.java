package page_object.pages.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object.misc.ConfProperties;

public class PracticeSignupPage {
    private final WebDriver driver;
    private PracticPage practicPage;
    private WebDriverWait wait;

    public PracticeSignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.practicPage = new PracticPage(driver);
        //TODO: use no hurdcode value for the timeout: 10
        this.wait = new WebDriverWait(driver, 10);
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
    public PracticeSignupPage openAccountPage(String url) {
        driver.get(url);

        return this;
    }
    /**
     * метод для заполнения поля email
     */
    public PracticeSignupPage fillLogin(String email) {
        //Wait email input, fill in
        wait.until(ExpectedConditions.visibilityOf(this.email)).sendKeys(email);

        return this;
    }
    /**
     * метод для заполнения поля pwd
     */
    public PracticeSignupPage fillPwd(String pwd) {
        //Fill in the pwd field
        this.pwd.sendKeys(pwd);

        return this;
    }
    /**
     * метод для заполнения поля click Submit
     */
    public PracticeSignupPage submitLogin() {
        //Click on the Submit button
        this.submitLogin.click();

        return this;
    }
    /**
     * метод для SignUp пользователя
     */
    public String loginedUserName() {
        //Return user name after succesfully login
        return wait.until(ExpectedConditions.visibilityOf(practicPage.getUserInfo())).getText();
    }
}
