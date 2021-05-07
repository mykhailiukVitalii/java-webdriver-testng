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

    /*
     * Factory method for getting browsers
     */
    public static WebDriver getBrowser(String browserName) {
        WebDriver driver = null;

        switch (browserName) {
            case "Chrome":
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
                break;
        }
        driver.manage().window().maximize();
        //задержка на выполнение теста = 3 сек. имплисит вейт как идеальные условия!!!!TODO:
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        return driver;
    }
    //Closing the WebDrivers
    public static void closeAllDriver() {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
        }
    }
}
