package cart;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTests {
    @Test
    public void testAddToAndRemoveFromCartIcon(){
        var homePage = loginPage.successfulLogin();
        var cartIcon = homePage.getCartIcon();
        homePage.clickAddToCart("Labs Bike");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "1" , "Incorrect Adding");
        homePage.clickAddToCart("Backpack");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "2" , "Incorrect Adding");
        homePage.clickAddToCart("Labs Bike");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "2" , "Incorrect Adding");
        homePage.clickRemove("Labs Bike");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "1" , "Incorrect Remove");
    }

    @Test
    public void testCorrectAddingToAndRemovingFromCartPage(){
        var homePage = loginPage.successfulLogin();
        var cartIcon = homePage.getCartIcon();
        homePage.clickAddToCart("Labs Bike");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "1" , "Incorrect Adding");
        homePage.clickAddToCart("Sauce Labs Backpack");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "2" , "Incorrect Adding");
        var cartPage = cartIcon.clickCartIcon();
        cartPage.clickRemove("Labs Bike");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "1" , "Incorrect Adding");
        cartPage.clickRemove("Sauce Labs Backpack");
    }

    @Test
    public void testClickingContinueShopping(){
        var homePage = loginPage.successfulLogin();
        var cartIcon = homePage.getCartIcon();
        homePage.clickAddToCart("Labs Onesie");
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , "1" , "Incorrect Adding");
        var cartPage = cartIcon.clickCartIcon();
        Assert.assertEquals(cartIcon.getNumbersOfItemsInCart() , String.valueOf(cartPage.getNumberOfItems()));
        cartPage.clickContinueShopping();
    }
}
