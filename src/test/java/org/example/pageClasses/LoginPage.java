package org.example.pageClasses;

import org.example.utilities.ManageWebDriver;
import org.example.views.ManageInput;
import org.openqa.selenium.By;

public class LoginPage {
    private final By emailInput = By.id("Email");
    private final By passwordInput = By.id("Password");
    private final By logInButton = By.xpath(".//input[@value='Log in']");
    ManageWebDriver manageWebDriver;

    public LoginPage(ManageWebDriver manageWebDriver) {
        this.manageWebDriver = manageWebDriver;
    }

    public void enterEmailAddress(String emailAddress) {
        new ManageInput(manageWebDriver.getElement(emailInput)).write(emailAddress);
    }

    public void enterPassword(String password) {
        new ManageInput(manageWebDriver.getElement(passwordInput)).write(password);
    }

    public void clickOnLoginButton(){
        manageWebDriver.clickAt(logInButton);
    }


}
