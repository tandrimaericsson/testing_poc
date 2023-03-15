package org.example.pageClasses;

import org.example.utilities.ManageWebDriver;
import org.openqa.selenium.By;

public class HomePage {
    private final By logInLink = By.linkText("Log in");
    private final By logOutLink = By.linkText("Log out");
    private final By currentUserName = By.xpath(".//a[@href='/customer/info']");
    private final By shoppingCartLabel = By.xpath(".//span[@class='cart-label' and text()='Shopping cart']");
    private final By shoppingCartQty = By.xpath(".//span[@class='cart-qty']");

    private final By computers = By.xpath(".//ul[@class='top-menu']//a[contains(text(),'Computers')]");
    private final By desktops = By.xpath(".//ul[@class='top-menu']//a[contains(text(),'Desktops')]");

    ManageWebDriver manageWebDriver;

    public HomePage(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public void clickOnLogIn() {
        manageWebDriver.clickAt(logInLink);
    }

    public void clickOnLogOut() {
        manageWebDriver.clickAt(logOutLink);
    }

    public void clickOnShoppingCartLabel() {
        manageWebDriver.mouseHoverAndClick(shoppingCartLabel);
    }

    public String getCurrentUserName() {
        return manageWebDriver.getElement(currentUserName).getText();
    }

    public Integer getShoppingCartQty() {
        String cartQtyValue = manageWebDriver.getElement(shoppingCartQty).getText();
        return Integer.parseInt(cartQtyValue.substring(1, cartQtyValue.length() - 1));
    }

    public void openDesktopFromMenu() {
        manageWebDriver.mouseHover(computers);
        manageWebDriver.mouseHoverAndClick(desktops);
    }

    public ProductItemWidget getProductItemWidget() {
        return new ProductItemWidget(manageWebDriver);
    }
}
