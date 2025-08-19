package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private By backButton = By.className("inventory_details_back_button");
    private By cartButton = By.xpath("/html/body/div/div[2]/div[2]/div/div/div/button");
    private By cartItemsNumber = By.xpath("/html/body/div/div[2]/div[1]/div[2]/a/span");

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickAddToCart(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        if(driver.findElement(cartButton).getText().contains("ADD TO CART")){
            driver.findElement(cartButton).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(cartItemsNumber));
        }
    }

    public void clickRemove(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        if(driver.findElement(cartButton).getText().contains("REMOVE")){
            driver.findElement(cartButton).click();
        }
    }

    public HomePage clickBack(){
        driver.findElement(backButton).click();
        return new HomePage(driver);
    }

    public String getNumbersOfItemsInCart(){
        return driver.findElement(cartItemsNumber).getText();
    }

    public boolean isCartNumberVisible(){
        try {
            return driver.findElement(cartItemsNumber).isDisplayed();
        }catch (NoSuchElementException e){
            System.out.println("Number box not displayed");
        }
        return false;
    }
}
