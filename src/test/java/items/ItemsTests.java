package items;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemsTests extends BaseTests {
    @Test
    public void testItemSauceBoltTShirt(){
        var homePage = loginPage.successfulLogin();
        var cartIcon = homePage.getCartIcon();
        var itemPage = homePage.clickItemImage("Sauce Labs Fleece Jacket");
        itemPage.clickAddToCart();
        Assert.assertEquals(itemPage.getNumbersOfItemsInCart() , "1");
        itemPage.clickAddToCart();
        Assert.assertEquals(itemPage.getNumbersOfItemsInCart() , "1");
        itemPage.clickRemove();
        itemPage.clickAddToCart();
        Assert.assertTrue(itemPage.isCartNumberVisible());
        itemPage.clickBack();
        Assert.assertTrue(cartIcon.isCartNumberVisible());
    }

}
