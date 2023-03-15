package org.example.views;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ManageInput {
    WebElement inputElement;

    public ManageInput(WebElement inputElement) {
        this.inputElement = inputElement;
    }

    public void clear() {
        inputElement.clear();
    }

    public void writeWithoutClear(String value) {
        inputElement.sendKeys(value);
    }

    public void write(String value) {
        clear();
        inputElement.sendKeys(value);
    }

    public String getValue() {
        return inputElement.getAttribute("value");
    }

    public void pressKey(Keys key) {
        inputElement.sendKeys(key);
    }
}
