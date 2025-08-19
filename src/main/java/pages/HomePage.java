package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CartIcon;
import utils.SideMenu;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class HomePage {
    private WebDriver driver;
    private By filterMenu = By.className("product_sort_container");
    private By itemsName = By.className("inventory_item_name");
    private By itemsPrice = By.className("inventory_item_price");
    private By cartButton = By.tagName("button");
    private By itemImage = By.className("inventory_item_img");
    private String filterOption;
    private ArrayList<String> itemsOrderedByName;
    private ArrayList<String> itemsPricesList;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }


//Filter Methods
    public void selectItemFilter(String option){
        filterOption = option;
        Select select = new Select(driver.findElement(filterMenu));
        select.selectByVisibleText(option);
        itemsOrderedByName = (ArrayList<String>) driver.findElements(itemsName).stream().map(WebElement::getText)
                .collect(Collectors.toList());
        itemsPricesList = (ArrayList<String>) driver.findElements(itemsPrice).stream().map(WebElement::getText)
                .collect(Collectors.toList());

    }

    public boolean orderCheck(){
        switch (filterOption){
            case "Name (A to Z)" :
                ArrayList<String> sortedName = new ArrayList<>(itemsOrderedByName);
                Collections.sort(sortedName , String.CASE_INSENSITIVE_ORDER);
               return itemsOrderedByName.equals(sortedName);

            case "Name (Z to A)" :
                ArrayList<String> reverseName = new ArrayList<>(itemsOrderedByName);
                Collections.sort(reverseName , Collections.reverseOrder());
                return itemsOrderedByName.equals(reverseName);

            case "Price (low to high)":
                ArrayList<String> sortedPrice = new ArrayList<>(itemsPricesList);
                Collections.sort(sortedPrice, (a, b) -> {
                    double priceA = Double.parseDouble(a.substring(1));
                    double priceB = Double.parseDouble(b.substring(1));
                    return Double.compare(priceA, priceB);
                });
                return itemsPricesList.equals(sortedPrice);

            case  "Price (high to low)" :
                ArrayList<String> reversePrice = new ArrayList<>(itemsPricesList);
                Collections.sort(reversePrice, (a, b) -> {
                    double priceA = Double.parseDouble(a.substring(1));
                    double priceB = Double.parseDouble(b.substring(1));
                    return Double.compare(priceB, priceA);
                });
                return itemsPricesList.equals(reversePrice);

        }
        return false;
    }

    //Items Methods
    private boolean isButtonDisplayed(String itemName  , String type){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        try {
            String xpath = "//div[@class='inventory_item'][.//div[@class='inventory_item_name'][contains(text(), '" + itemName + "')]]";
            WebElement target = driver.findElement(By.xpath(xpath));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(target.findElement(cartButton)));
            String buttonText = button.getText().toUpperCase();
            return buttonText.contains(type.toUpperCase());
        }catch (NoSuchElementException e){
            System.out.println("Item containing '" + itemName + "' not found");
        }
        return false;
    }

    public void clickAddToCart(String itemName){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        if(isButtonDisplayed(itemName , "ADD TO CART")){
           try {
               String xpath = "//div[@class='inventory_item'][.//div[@class='inventory_item_name'][contains(text(), '" + itemName + "')]]";
               WebElement target = driver.findElement(By.xpath(xpath));
               WebElement button = wait.until(ExpectedConditions.elementToBeClickable(target.findElement(cartButton)));
               button.click();
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }

           } catch (NoSuchElementException e) {
               System.out.println("Item containing '" + itemName + "' not found");
           }
       }
        }

    public void clickRemove(String itemName){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        if(isButtonDisplayed(itemName , "REMOVE")){
            try {
                String xpath = "//div[@class='inventory_item'][.//div[@class='inventory_item_name'][contains(text(), '" + itemName + "')]]";
                WebElement target = driver.findElement(By.xpath(xpath));
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(target.findElement(cartButton)));
                button.click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } catch (NoSuchElementException e) {
                System.out.println("Item containing '" + itemName + "' not found");
            }
        }
        }

    public ProductPage clickItem(String itemName){
        driver.findElement(By.linkText(itemName)).click();
        return new ProductPage(driver);
    }

    public ProductPage clickItemImage(String itemName){
        try {
            String xpath = "//div[@class='inventory_item'][.//div[@class='inventory_item_name'][contains(text(), '" + itemName + "')]]";
            WebElement target = driver.findElement(By.xpath(xpath));
            target.findElement(itemImage).click();

        } catch (NoSuchElementException e) {
            System.out.println("Item containing '" + itemName + "' not found");
        }
        return new ProductPage(driver);
    }

    public SideMenu getSideMenu(){
        return new SideMenu(driver);
    }
    public CartIcon getCartIcon(){
        return new CartIcon(driver);
    }

    public void dismissPasswordSavePrompt() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().dismiss();
        } catch (TimeoutException e) {
        }
    }












}




