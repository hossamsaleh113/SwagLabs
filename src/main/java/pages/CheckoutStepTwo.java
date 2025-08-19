package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class CheckoutStepTwo {
    private WebDriver driver;
    private By paymentInfo = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[2]");
    private By shippingInfo = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[4]");
    private By subTotal = By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/div[5]/text()[2]");
    private By tax = By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/div[6]/text()[2]");
    private By total = By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]/text()[2]");
    private By totalItemsSum = By.className("inventory_item_price");
    private By cancelButton = By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/div[8]/a[1]");
    private By finishButton = By.xpath("/html/body/div/div[2]/div[3]/div/div[2]/div[8]/a[2]");

    public CheckoutStepTwo(WebDriver driver){
        this.driver = driver;
    }

    public ProductPage clickItem(String itemName){
        driver.findElement(By.linkText(itemName)).click();
        return new ProductPage(driver);
    }

    public String getPaymentInfo(){
        return driver.findElement(paymentInfo).getText();
    }

    public String getShippingInfo(){
        return driver.findElement(shippingInfo).getText();
    }

    public String getSumOfCashOutItems(){
        List<String> prices = driver.findElements(totalItemsSum).stream().map(WebElement::getText)
                .toList();
        double sum=0;
        for(var price : prices){
            sum += Double.parseDouble(price.substring(1));
        }
        return ("$" + sum);
    }

    public String getSubTotal(){
        return driver.findElement(subTotal).getText();
    }

    public String getTax(){
        return driver.findElement(tax).getText();
    }

    public String getTotal(){
        return driver.findElement(total).getText();
    }

    public HomePage clickCancel(){
        driver.findElement(cancelButton).click();
        return new HomePage(driver);
    }

    public OrderCompletePage clickFinish(){
        driver.findElement(finishButton).click();
        return new OrderCompletePage(driver);
    }


}
