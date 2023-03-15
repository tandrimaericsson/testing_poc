package org.example.pageClasses;

import org.example.utilities.ManageWebDriver;
import org.example.views.ManageInput;
import org.openqa.selenium.By;

public class ProductDetailsWidget {
    private final By productDetailsPage = By.id("product-details-form");
    private final By addToCart = By.xpath(".//input[@value='Add to cart']");
    private final By productQuantity = By.xpath(".//input[starts-with(@class,'qty-input')]");
    private final By notification = By.xpath(".//div[@id='bar-notification']/p");
    ManageWebDriver manageWebDriver;

    public ProductDetailsWidget(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public void addProductToCart() {
        manageWebDriver.clickAt(manageWebDriver.getElement(productDetailsPage), addToCart);
    }

    public void enterQuantity(String quantity) {
        new ManageInput(manageWebDriver.getElement(manageWebDriver.getElement(productDetailsPage), productQuantity)).write(quantity);
    }

    public String getNotificationText(){
        return manageWebDriver.getElement(notification).getText();
    }
}
