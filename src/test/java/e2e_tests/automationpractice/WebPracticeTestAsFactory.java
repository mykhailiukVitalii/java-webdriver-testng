package e2e_tests.automationpractice;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import page_object.misc.BrowserFactory;
import page_object.misc.ConfProperties;
import page_object.pages.PracticPage;
import page_object.pages.PracticeAccountPage;
import page_object.pages.PracticeDressPage;

import java.util.concurrent.TimeUnit;

public class WebPracticeTestAsFactory {
    //TODO: use .specs for tests!!!!!!!
    private WebDriver driver;
    private PracticPage practicPage;
    private PracticeDressPage pdPage;
    private PracticeAccountPage accdPage;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeSuite
    public void initDriver() {
        driver = BrowserFactory.getBrowser("Chrome");
        practicPage = new PracticPage(driver);
        pdPage = new PracticeDressPage(driver);
        accdPage = new PracticeAccountPage(driver);
    }

    @AfterSuite
    public void quitDriver() throws Exception {
        BrowserFactory.closeAllDriver();
    }

    @Test
    public void loginToPractice() {
        // Open Google home page
        accdPage.openAccountPage();
        //Login to App
        String username = accdPage.signUp(
                ConfProperties.getProperty("pLogin"),
                ConfProperties.getProperty("pPasword"),
                Integer.parseInt(ConfProperties.getProperty("defaultTimeout")));

        Assert.assertEquals(username, ConfProperties.getProperty("pname"));
    }

    @Test(dependsOnMethods = { "loginToPractice" })
    public void confirmAvailableDressCount() {
        pdPage.openSummerDressesPage();

        Assert.assertEquals(
                pdPage.productList(Integer.parseInt(ConfProperties.getProperty("defaultTimeout"))),
                3);
    }
}
