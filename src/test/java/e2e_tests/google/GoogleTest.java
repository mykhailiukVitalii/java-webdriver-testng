package e2e_tests.google;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import page_object.misc.ConfProperties;
import page_object.p_components.GoogleResults;
import page_object.p_components.GoogleTopbar;
import page_object.pages.GooglePage;

import java.util.concurrent.TimeUnit;

public class GoogleTest {
    //TODO: use .specs for tests!!!!!!!
    private GooglePage gPage;
    public GoogleTopbar gTopbar;
    public GoogleResults gResults;
    private WebDriver driver;

    /**
     * осуществление первоначальной настройки
     */
    @BeforeSuite
    public void initDriver() {
        //set chrome options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //определение пути до драйвера и его настройка
//        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("winchromedriver"));
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        //initializing classes for the test
        driver = new ChromeDriver();
        gPage = new GooglePage(driver);
        gTopbar = new GoogleTopbar(driver);
        gResults = new GoogleResults(driver);
        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 3 сек. имплисит вейт как идеальные условия!!!!TODO:
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //TODO: as browser.pause?
        //TODO: не заострял на смене локации браузера
        // Open Google home page
        driver.get(ConfProperties.getProperty("homeGooglepage"));
    }

    @AfterSuite
    public void quitDriver() throws Exception {
        driver.quit();
    }

    @Test
    public void confirmSearching() {
        gPage.fillTextAndFind("selenide org"); //TODO: separate file with this text
        //active img TAB
        gTopbar.activeImgTab();
        //active all tab
        gTopbar.activeAllTab();
        //assert //TODO: add browser.pause()
        Assert.assertEquals(gResults.searchResultByIndex(1), "https://selenide.org");
    }
}
