package org.example.testClasses;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TricentisAppTest extends WithBrowserTest {

    public TricentisAppTest() {
        super();
    }

    @Parameters({"email", "password"})
    @Test
    public void testApp(String email, String password) {
        final String notificationText = "The product has been added to your shopping cart";
        final String billingAddress = "planit test, madhapur, Hyderabad 500017, India";
        final String checkoutInformation = "You will pay by COD";
        final String orderCompletionMessage = "Your order has been successfully processed!";
        final String productId = "72";
        final int productRowInCart = 1;
        final String productQty = "5";

        homePage.clickOnLogIn();
        loginPage.enterEmailAddress(email);
        loginPage.enterPassword(password);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(homePage.getCurrentUserName(), email);
        clearShoppingCart();
        homePage.openDesktopFromMenu();
        homePage.getProductItemWidget().openProductItem(productId);
        homePage.getProductItemWidget().getProductDetailsWidget().enterQuantity(productQty);
        homePage.getProductItemWidget().getProductDetailsWidget().addProductToCart();
        Assert.assertEquals(homePage.getProductItemWidget().getProductDetailsWidget().getNotificationText(), notificationText);
        homePage.clickOnShoppingCartLabel();
        Float unitPrice=Float.parseFloat(shoppingCartPage.getUnitPriceForProduct(productRowInCart));
        Assert.assertEquals(Float.parseFloat(shoppingCartPage.getSubTotalPrice()),Integer.parseInt(productQty)*unitPrice);
        shoppingCartPage.tickTermsOfServiceCheckBox();
        shoppingCartPage.checkOut();
        checkoutPage.getBillingAddress().selectByVisibleText(billingAddress);
        checkoutPage.clickOnContinue();
        checkoutPage.getShippingAddress().selectByVisibleText(billingAddress);
        checkoutPage.clickOnContinue();
        checkoutPage.tickGoByAir();
        checkoutPage.clickOnContinue();
        checkoutPage.clickOnContinue();
        Assert.assertEquals(checkoutPage.getCheckoutInformation(), checkoutInformation);
        checkoutPage.clickOnContinue();
        checkoutPage.clickOnConfirm();
        Assert.assertEquals(checkoutPage.getOrderCompletionMessage(), orderCompletionMessage);
        System.out.printf("Order number is ".concat(checkoutPage.getOrderNumber()));
        checkoutPage.clickOnContinue();
    }

    private void clearShoppingCart() {
        if (homePage.getShoppingCartQty() > 0) {
            homePage.clickOnShoppingCartLabel();
            shoppingCartPage.tickAllRemoveFromCartCheckBox();
            shoppingCartPage.clickOnUpdateShoppingCart();
        }
    }
}
