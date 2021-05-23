package page_object.misc;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
    //driver for current driver session
    private static WebDriver driver = null;
    /*
     * Factory method for getting browsers
     */
    public static WebDriver getBrowser(String browserName) {
        switch (browserName) {
            case "Firefox":
                //use Firefox as example
                break;
            default:
                driver = drivers.get("Chrome");
                if (driver == null) {
                    //set chrome options
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    //определение пути до драйвера и его настройка
                    System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("winchromedriver"));
                    driver = new ChromeDriver();
                    drivers.put("Chrome", driver);
                }
        }
        driver.manage().window().maximize();
        //задержка на выполнение теста = 5 сек. имплисит вейт как идеальные условия!!!!TODO:
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver;
    }
    //Closing the WebDrivers
    public static void closeAllDriver() {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
        }
    }
    //Closing only exist session
    public static void closeDriver() {
        driver.quit();
    }
    //TODO: as Additional browser option
//    /**
//     * Creates {@link WebDriver} instance with timeout and browser window configurations.
//     *
//     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
//     * to the automation project, returns {@link ChromeDriver} instance by default.
//     */
//    public static EventFiringWebDriver getConfiguredDriver() throws UnsupportedOperationException {
//        WebDriver driver = getDriver();
//        //driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        EventFiringWebDriver wrappedDriver = new EventFiringWebDriver(driver);
//
//        wrappedDriver.register(new EventHandler());
//
//        return wrappedDriver;
//    }
}
