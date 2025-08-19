package sidemenu;

import base.BaseTests;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SideMenuTests extends BaseTests {
    @Test
    public void testClickAllItems(){
        var homePage = loginPage.successfulLogin();
        var sideMenu = homePage.getSideMenu();
        sideMenu.clickAllItems();
    }

    @Test
    public void testClickAbout(){
        var homePage = loginPage.successfulLogin();
        var sideMenu = homePage.getSideMenu();
        sideMenu.clickAbout();
        Assert.assertEquals(sideMenu.getPageTitle() ,
                "Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing");
    }

    @Test
    public void testClickLogout(){
        var homePage = loginPage.successfulLogin();
        var sideMenu = homePage.getSideMenu();
        sideMenu.clickLogout();
        Assert.assertTrue(sideMenu.isDisplayed(By.id("login-button")));
    }

    @Test
    public void clickResetAppState(){
        var homePage = loginPage.successfulLogin();
        var sideMenu = homePage.getSideMenu();
        sideMenu.clickResetAppState();
    }
}
