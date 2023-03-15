package org.example.pageClasses;

import org.example.utilities.ManageWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage {
    private final By removeFromCartCheckBox = By.name("removefromcart");
    private final By termsOfServiceCheckBox = By.id("termsofservice");
    private final By checkoutButton = By.id("checkout");
    private final By updateShoppingCart = By.name("updatecart");
    private final By subTotal = By.xpath("(.//span[@class='product-price'])[1]");
    private final By productUnitPrice = By.xpath(".//span[@class='product-unit-price']");
    private final String productRowInCart = ".//table/tbody//tr[%s]";

    ManageWebDriver manageWebDriver;

    public ShoppingCartPage(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public void tickAllRemoveFromCartCheckBox() {
        List<WebElement> removeFromCartCheckBoxs = manageWebDriver.getElements(removeFromCartCheckBox);
        removeFromCartCheckBoxs.forEach(checkBox -> checkBox.click());
    }

    public void clickOnUpdateShoppingCart(){
        manageWebDriver.clickAt(updateShoppingCart);
    }

    public String getSubTotalPrice(){
        return manageWebDriver.getElement(subTotal).getText();
    }

    public String getUnitPriceForProduct(int row){
        return manageWebDriver.getElement(getProductRowInCart(row),productUnitPrice).getText();
    }

    private WebElement getProductRowInCart(int row){
        return manageWebDriver.getElement(By.xpath(String.format(productRowInCart,row)));
    }

    public void tickTermsOfServiceCheckBox() {
        manageWebDriver.clickAt(termsOfServiceCheckBox);
    }

    public void checkOut(){
        manageWebDriver.clickAt(checkoutButton);
    }
}
