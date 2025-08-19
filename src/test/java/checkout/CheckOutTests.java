package checkout;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTests extends BaseTests {
    @Test
    public void testSuccessfulCheckout(){
        var homePage = loginPage.successfulLogin();
        homePage.dismissPasswordSavePrompt();
        var cartIcon = homePage.getCartIcon();
        homePage.clickAddToCart("Sauce Labs Fleece Jacket");
        var cartPage = cartIcon.clickCartIcon();
        var checkoutPage = cartPage.clickCheckout();
        checkoutPage.fillAllFields("Hossam" , "Saleh" , "1998");
        var conCheckout = checkoutPage.clickContinue();
        var finalPage = conCheckout.clickFinish();
        Assert.assertEquals(finalPage.getTextMassage() , "THANK YOU FOR YOUR ORDER");
    }

    @Test
    public void testMissingFirstName(){
        var homePage = loginPage.successfulLogin();
        var cartIcon = homePage.getCartIcon();
        var checkoutPage = cartIcon.clickCartIcon();
        var fillInfoPage = checkoutPage.clickCheckout();
        fillInfoPage.fillAllFields("" , "Hossam" , "1111");
        fillInfoPage.clickContinue();
        Assert.assertEquals(fillInfoPage.getErrorMassage() , "Error: First Name is required"
                , "Incorrect Error Massage!");
    }

    @Test
    public void testMissingLastname(){
        var homePage = loginPage.successfulLogin();
        var cartIcon = homePage.getCartIcon();
        var checkoutPage = cartIcon.clickCartIcon();
        var fillInfoPage = checkoutPage.clickCheckout();
        fillInfoPage.fillAllFields("Hossam" , "" , "1111");
        fillInfoPage.clickContinue();
        Assert.assertEquals(fillInfoPage.getErrorMassage() , "Error: Last Name is required"
                , "Incorrect Error Massage!");
    }

    @Test
    public void testMissingZip(){
        var homePage = loginPage.successfulLogin();
        var cartIcon = homePage.getCartIcon();
        var checkoutPage = cartIcon.clickCartIcon();
        var fillInfoPage = checkoutPage.clickCheckout();
        fillInfoPage.fillAllFields("Saleh" , "Hossam" , "");
        fillInfoPage.clickContinue();
        Assert.assertEquals(fillInfoPage.getErrorMassage() , "Error: Postal Code is required"
                , "Incorrect Error Massage!");
    }

    @Test
    public void testMovingBetweenCheckoutPages(){
        var homePage = loginPage.successfulLogin();
        homePage.clickAddToCart("Sauce Labs Fleece Jacket");
        homePage.clickAddToCart("Backpack");
        var cartIcon = homePage.getCartIcon();
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "2" , "Incorrect number");
        var checkoutPage = cartIcon.clickCartIcon();
        Assert.assertEquals(String.valueOf(checkoutPage.getNumberOfItems()) , cartIcon.getNumbersOfItemsInCart());
        checkoutPage.clickRemove("Backpack");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "1" , "Incorrect number");
        var fillInfoPage = checkoutPage.clickCheckout();
        checkoutPage = fillInfoPage.clickCancel();
        checkoutPage.clickRemove("Sauce Labs Fleece Jacket");

    }


}
