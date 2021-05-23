package e2e_tests.automationpractice;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import page_object.misc.BrowserFactory;
import page_object.misc.ConfProperties;
import page_object.pages.practice.PracticPage;
import page_object.pages.practice.PracticeSignupPage;
import page_object.pages.practice.PracticeDressPage;

public class WebPracticeTestDressItemsNumber {
    //TODO: use .specs for tests!!!!!!!
    private WebDriver driver;
    private PracticPage practicPage;
    private PracticeDressPage pdPage;
    private PracticeSignupPage signupPage;
    private String openUrl, dressUrl;
    private String userName;
    private String login, pwd;
    private Number defTimeout;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeSuite
    public void initDriver() {
        driver = BrowserFactory.getBrowser("Chrome");
        practicPage = new PracticPage(driver);
        pdPage = new PracticeDressPage(driver);
        signupPage = new PracticeSignupPage(driver);
        //TODO: не нравится что сильно много доп строчек, можно ли их апдейтить как то более кратко?
        //Get data from the Config file
        openUrl = ConfProperties.getProperty("accountPracticepage");
        dressUrl = ConfProperties.getProperty("summerDressPracticepage");
        login = ConfProperties.getProperty("pLogin");
        pwd = ConfProperties.getProperty("pPasword");
        defTimeout = Integer.parseInt(ConfProperties.getProperty("defaultTimeout"));
        userName = ConfProperties.getProperty("pname");
    }

    @AfterSuite
    public void quitDriver() throws Exception {
        BrowserFactory.closeAllDriver();
    }

    @Test
    public void loginToPractice() {
        //Step 1 - Open Login page, fill in email/pwd and submit
        String username = signupPage
                        .openAccountPage(openUrl)
                        .fillLogin(login)
                        .fillPwd(pwd)
                        .submitLogin()
                        .loginedUserName();
        //username should be displayed in the TOP section after logging in
        Assert.assertEquals(username, userName);
        //Step 2 - Go to Dress page and check default results count
        int pCount = pdPage
                        .openSummerDressesPage(dressUrl)
                        .productList();
        //Dress page should contain 3 items
        Assert.assertEquals(pCount,3);
    }
}
