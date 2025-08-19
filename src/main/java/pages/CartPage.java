package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class CartPage {
    private WebDriver driver;
    private By conShop = By.className("btn_secondary");
    private By items = By.className("cart_item");
    private By removeButton = By.xpath(".//button[contains(@class,'btn_secondary')]");
    private By checkoutButton = By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/a[2]");

    public CartPage(WebDriver driver){
        this.driver = driver;
    }
    public HomePage clickContinueShopping(){
        driver.findElement(conShop).click();
        return new HomePage(driver);
    }

    public int getNumberOfItems(){
        return driver.findElements(items).size();
    }

    public void clickRemove(String itemName){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        try {
            String xpath = "//div[@class='cart_item'][.//div[@class='inventory_item_name'][contains(text(), '" + itemName + "')]]";
            WebElement cartItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(cartItem.findElement(this.removeButton)));
            removeButton.click();

        } catch (NoSuchElementException e) {
            System.out.println("Item containing '" + itemName + "' not found");
        }


    }

    public CheckoutStepOne clickCheckout(){
        driver.findElement(checkoutButton).click();
        return new CheckoutStepOne(driver);
    }
}
