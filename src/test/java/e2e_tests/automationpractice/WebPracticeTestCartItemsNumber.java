package e2e_tests.automationpractice;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import page_object.misc.BrowserFactory;
import page_object.misc.ConfProperties;
import page_object.misc.Uttils;
import page_object.pages.practice.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebPracticeTestCartItemsNumber {
    private WebDriver driver;
    private PracticeWomenPage pwPage;
    private PracticeCartPage pcPage;
    private PracticeDetailPage pdetailPage;
    private PracticeMyAccountPage myAccount;
    private Uttils ut;
    private PracticeSignupPage signupPage;
    private String openUrl;
    private String userName;
    private String login, pwd;
    /**
     * осуществление первоначальной настройки
     */
    @BeforeSuite
    public void initDriver() {
        driver = BrowserFactory.getBrowser("Chrome");
        signupPage = new PracticeSignupPage(driver);
        myAccount = new PracticeMyAccountPage(driver);
        pwPage = new PracticeWomenPage(driver);
        pcPage = new PracticeCartPage(driver);
        pdetailPage = new PracticeDetailPage(driver);
        ut = new Uttils();
        //TODO: не нравится что сильно много доп строчек, можно ли их апдейтить как то более кратко?
        //Get data from the Config file
        openUrl = ConfProperties.getProperty("accountPracticepage");
        login = ConfProperties.getProperty("pLogin");
        pwd = ConfProperties.getProperty("pPasword");
        userName = ConfProperties.getProperty("pname");
    }

    @AfterSuite
    public void quitDriver() throws Exception {
        //close browser sessions
        BrowserFactory.closeAllDriver();
    }
    @AfterMethod
    public void screenMet(ITestResult result) throws Exception {
        try {
            if(result.getStatus() == ITestResult.FAILURE) {
                //TODO: не сейвится скриншот... обсудить єтот момент
//                ut.takeSnapShot(driver, "test-fail.png");
                System.out.println("fail **********");
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //TODO: обсудить о нейминге функций для названия кейсов + названия переменніх?????
    @Test
    public void evenSecondItemToCart() {
        //Step 1 - Open Login page, fill in email/pwd and submit
        String username = signupPage
                        .openAccountPage(openUrl)
                        .fillLogin(login)
                        .fillPwd(pwd)
                        .submitLogin()
                        .loginedUserName();
        //username should be displayed in the TOP section after logging in
        Assert.assertEquals(username, userName);
        //Step 2 - women tab opened
        myAccount.openWomenTab();
        //Step 3 -Add item to Cart
        List<String> addedList = new ArrayList<String>();
        for (int i = 0; i < pwPage.itemsListCount(); i++) {
            if(i%2 == 1){
                int index = i;
                pwPage
                        .waitFirstExistItem(index)
                        .openItemPage(index);
                String successMessage = pdetailPage
                        .clickOnAddToCart(index)
                        .getSuccessMessage();
                //Inner assert
                Assert.assertEquals(successMessage, "Product successfully added to your shopping cart");
                String itemName = pdetailPage
                        .productName();
                System.out.println("Item name" + "[" + i + "]: " + itemName);
                addedList.add(itemName);
                driver.navigate().back();
            }
        }
        //Step 4 -  Get all product names from the Cart page
        List<String> cartList = pcPage
                .openCart()
                .nameFromCartList();
        //Expected result: Previously added products are displayed in the cart
        Collections.sort(addedList);
        Collections.sort(cartList);
        Assert.assertTrue( addedList.equals(cartList));
    }
    //TODO: как заранить все тесты как один сьют - ?????обсдуить!!!!
}

//    public void invokeScreenshotMethod(ItestResult res)
//    {
//        if (ItestResult.Failure==res.getStatus()){
//            try{
//                TakeScreenshot ts=(TakeScreenShot) driver;
//
//                File src= ts.getScreenshotAs(OUTPUTTYPE.FILE);
//                FileUtils.copyFile(src, new File("e://destination
//                        location+"res.getName()+".png");
//            }
//
//            Catch(Exception e)
//            {
//
//                System.out.println("");
//            }
//        }