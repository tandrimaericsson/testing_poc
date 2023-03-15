package org.example.pageClasses;

import org.example.utilities.ManageWebDriver;
import org.example.views.ManageDropdownList;
import org.openqa.selenium.By;

public class CheckoutPage {
    private final By billingAddress = By.id("billing-address-select");
    private final By shippingAddress = By.id("shipping-address-select");
    private final By continueButton = By.xpath(".//li[starts-with(@class,'tab-section') and contains(@class,'active')]//input[@value='Continue']|.//div[@class='section order-completed']//input[@value='Continue']");
    private final By confirmButton = By.xpath(".//li[starts-with(@class,'tab-section') and contains(@class,'active')]//input[@value='Confirm']");
    private final By checkoutInformation = By.xpath(".//div[@class='info']");
    private final By orderCompletionMessage = By.xpath(".//div[@class='section order-completed']//div[@class='title']");
    private final By orderNumber = By.xpath(".//div[@class='section order-completed']//*[@class='details']");
    private final By goByAirRadioButton = By.id("shippingoption_1");
    ManageWebDriver manageWebDriver;

    public CheckoutPage(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public ManageDropdownList getBillingAddress() {
        return new ManageDropdownList(manageWebDriver.getElement(billingAddress));
    }

    public ManageDropdownList getShippingAddress() {
        return new ManageDropdownList(manageWebDriver.getElement(shippingAddress));
    }

    public void clickOnContinue() {
        manageWebDriver.clickAt(continueButton);
    }

    public void clickOnConfirm() {
        manageWebDriver.clickAt(confirmButton);
    }

    public String getCheckoutInformation() {
        return manageWebDriver.getElement(checkoutInformation).getText();
    }

    public String getOrderCompletionMessage() {
        return manageWebDriver.getElement(orderCompletionMessage).getText();
    }

    public String getOrderNumber() {
        return manageWebDriver.getElement(orderNumber).getText().trim().split(":")[1].split("\n")[0].trim();
    }

    public void tickGoByAir() {
        if (!manageWebDriver.getElement(goByAirRadioButton).isSelected()) manageWebDriver.clickAt(goByAirRadioButton);
    }
}
