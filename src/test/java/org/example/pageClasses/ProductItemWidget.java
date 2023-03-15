package org.example.pageClasses;

import org.example.utilities.ManageWebDriver;
import org.openqa.selenium.By;

public class ProductItemWidget {
    private final String productItemLocator = ".//div[@data-productid='%s']";
    private final String addToCart = productItemLocator.concat("//input[@value='Add to cart']");
    private final By productLink = By.xpath(".//div[@class='details']//h2");
    ManageWebDriver manageWebDriver;

    public ProductItemWidget(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public void addProductToCart(String productId) {
        manageWebDriver.clickAt(By.xpath(String.format(addToCart, productId)));
    }

    public void openProductItem(String productId) {
        manageWebDriver.clickAt(manageWebDriver.getElement(By.xpath(String.format(productItemLocator, productId))), productLink);
    }

    public ProductDetailsWidget getProductDetailsWidget() {
        return new ProductDetailsWidget(manageWebDriver);
    }
}
