package page_object.misc;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
//TODO: специально сделал ошибку что бы не конфл имена....
public class Uttils {
    /**
     * This function will take screenshot
     * @param webdriver
     * @param filename
     * @throws Exception
     */
    public static void takeSnapShot(WebDriver webdriver, String filename) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        String userDirectory = System.getProperty("user.dir");
        String path = userDirectory + filename;
        File DestFile=new File(path);
        System.out.println("Screen has been saved! path =" + path);
    }
}
