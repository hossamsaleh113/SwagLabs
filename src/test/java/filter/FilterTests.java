package filter;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterTests extends BaseTests {
    @Test
    public void testFilterNameAtoZ(){
        var homePage = loginPage.successfulLogin();
        homePage.selectItemFilter("Name (A to Z)");
        Assert.assertTrue(homePage.orderCheck());

    }

    @Test
    public void testFilterNameZtoA(){
        var homePage = loginPage.successfulLogin();
        homePage.selectItemFilter("Name (Z to A)");
        Assert.assertTrue(homePage.orderCheck());
    }

    @Test
    public void testFilterPriceHighToLow(){
        var homePage = loginPage.successfulLogin();
        homePage.selectItemFilter("Price (high to low)");
        Assert.assertTrue(homePage.orderCheck());
    }

    @Test
    public void testFilterPriceLowToHigh(){
        var homePage = loginPage.successfulLogin();
        homePage.selectItemFilter("Price (low to high)");
        Assert.assertTrue(homePage.orderCheck());
    }
}
