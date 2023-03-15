package org.example.testClasses;

import org.example.pageClasses.CheckoutPage;
import org.example.pageClasses.HomePage;
import org.example.pageClasses.LoginPage;
import org.example.pageClasses.ShoppingCartPage;
import org.example.utilities.ManagePropertiesFile;
import org.example.utilities.ManageWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class WithBrowserTest {
    ManageWebDriver manageWebDriver;
    HomePage homePage;
    LoginPage loginPage;
    ShoppingCartPage shoppingCartPage;
    CheckoutPage checkoutPage;

    public WithBrowserTest() {
        String url = null;
        String browser = null;
        try {
            url = new ManagePropertiesFile().getPropertyValue("src/resources/config.properties", "applicationUrl");
            browser = new ManagePropertiesFile().getPropertyValue("src/resources/config.properties", "browser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        manageWebDriver = new ManageWebDriver(url, browser);
    }

    @BeforeTest
    public void initializePageObjects() {
        homePage = new HomePage(manageWebDriver);
        loginPage = new LoginPage(manageWebDriver);
        shoppingCartPage = new ShoppingCartPage(manageWebDriver);
        checkoutPage = new CheckoutPage(manageWebDriver);
    }

    @BeforeMethod
    public void openBrowser() throws Exception {
        manageWebDriver.startDriver();
        manageWebDriver.maximizeBrowser();
    }

    @AfterMethod
    public void closeBrowser() {
        homePage.clickOnLogOut();
        manageWebDriver.quitBrowser();
    }
}
