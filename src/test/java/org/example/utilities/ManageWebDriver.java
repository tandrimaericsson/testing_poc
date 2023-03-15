package org.example.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ManageWebDriver {
    private final String url;
    private final String browser;
    private WebDriver webDriver;

    public ManageWebDriver(String url, String browser) {
        this.url = url;
        this.browser = browser;
    }

    public WebDriver startDriver() throws Exception {
        if (browser.equals("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver(chromeOptions);
            webDriver.get(url);
        } else {
            throw new Exception(browser + " is not supported right now..");
        }
        return webDriver;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void maximizeBrowser() {
        if (getWebDriver() != null)
            getWebDriver().manage().window().maximize();
    }

    public void quitBrowser() {
        if (getWebDriver() != null)
            getWebDriver().quit();
    }

    public void switchTab(int tabIndex) {
        List<String> tabs = webDriver.getWindowHandles().stream().collect(Collectors.toList());
        webDriver.switchTo().window(tabs.get(tabIndex));
    }

    public void closeOthersTab(int currentTabIndex) {
        List<String> tabs = webDriver.getWindowHandles().stream().collect(Collectors.toList());
        String currentId = tabs.get(currentTabIndex);

        for (String tab : tabs) {
            webDriver.switchTo().window(tab);
            if (!tab.equals(currentId)) webDriver.close();
        }

        webDriver.switchTo().window(currentId);
    }

    public WebElement getElement(By elementLocator) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        try {
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (Exception e) {
            throw new RuntimeException("Element not found\n".concat(e.toString()));
        }
        return element;
    }

    public WebElement getElement(WebElement parent, By elementLocator) {
        return parent.findElement(elementLocator);
    }

    public List<WebElement> getElements(By elementLocator) {
        List<WebElement> elements = null;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        try {
            elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementLocator));
        } catch (Exception e) {
            throw new RuntimeException("Element not found\n".concat(e.toString()));
        }
        return elements;
    }

    public boolean isElementPresent(By elementLocator) {
        return getElement(elementLocator) != null;
    }

    public void clickAt(By elementLocator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elementLocator)).click();
        } catch (Exception e) {
            throw new RuntimeException("Element not clickable\n".concat(e.toString()));
        }
    }

    public void clickAt(WebElement parent, By elementLocator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(parent.findElement(elementLocator))).click();
        } catch (Exception e) {
            throw new RuntimeException("Element not clickable\n".concat(e.toString()));
        }
    }

    public void mouseHover(By elementLocator) {
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(getElement(elementLocator)).build().perform();
    }

    public void mouseHoverAndClick(By elementLocator) {
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(getElement(elementLocator)).click().build().perform();

    }
}
