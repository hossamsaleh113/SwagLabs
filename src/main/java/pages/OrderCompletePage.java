package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderCompletePage {
    private WebDriver driver;
    private By massage = By.className("complete-header");

    public OrderCompletePage(WebDriver driver){
        this.driver = driver;
    }

    public String getTextMassage(){
        return driver.findElement(massage).getText();
    }


}
