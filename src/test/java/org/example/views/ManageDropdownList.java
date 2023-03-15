package org.example.views;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

public class ManageDropdownList {
    Select selectDropdownList;

    public ManageDropdownList(WebElement dropdownList) {
        this.selectDropdownList = new Select(dropdownList);
    }

    public void selectByVisibleText(String value) {
        selectDropdownList.selectByVisibleText(value);
    }

    public String getSelectedValue() {
        return selectDropdownList.getFirstSelectedOption().getText();
    }
}
