package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;

import java.time.Duration;

public class CartIcon {
    private WebDriver driver;
    private By cartIcon = By.id("shopping_cart_container");
    private By cartItemsNumber = By.xpath("/html/body/div/div[2]/div[1]/div[2]/a/span");
    public CartIcon(WebDriver driver){
        this.driver = driver;
    }
    public CartPage clickCartIcon(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        driver.findElement(cartIcon).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/a[2]")));
        return new CartPage(driver);
    }

    public String getNumbersOfItemsInCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(cartItemsNumber));
        return driver.findElement(cartItemsNumber).getText();
    }

    public boolean isCartNumberVisible(){
        try {
            return driver.findElement(cartItemsNumber).isDisplayed();
        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("Number box not displayed");
        }
        return false;
    }
}
