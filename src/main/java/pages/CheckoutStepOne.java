package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutStepOne {
    private WebDriver driver;
    private By fName = By.id("first-name");
    private By lName = By.id("last-name");
    private By zipCode = By.id("postal-code");
    private By continueButton = By.xpath("/html/body/div/div[2]/div[3]/div/form/div[2]/input");
    private By msgField = By.tagName("h3");
    private By cancelButton = By.xpath("/html/body/div/div[2]/div[3]/div/form/div[2]/a");


    public CheckoutStepOne(WebDriver driver) {
        this.driver = driver;
    }

    private void enterFirstName(String firstName) {
        driver.findElement(fName).sendKeys(firstName);
    }

    private void enterLastName(String lastName) {
        driver.findElement(lName).sendKeys(lastName);
    }

    private void enterZip(String zip) {
        driver.findElement(zipCode).sendKeys(zip);
    }

    public void fillAllFields(String firstName, String lastName, String zip) {
        try {
            driver.findElement(fName).clear();
            enterFirstName(firstName);
            Thread.sleep(1000);
            driver.findElement(lName).clear();
            enterLastName(lastName);
            Thread.sleep(1000);
            driver.findElement(zipCode).clear();
            enterZip(zip);
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException("Hessa");
        }

    }

    public CheckoutStepTwo clickContinue() {
        driver.findElement(continueButton).click();
        return new CheckoutStepTwo(driver);
    }

    public String getErrorMassage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(msgField));
        return driver.findElement(msgField).getText();
    }

    public CartPage clickCancel() {
        driver.findElement(cancelButton).click();
        return new CartPage(driver);
    }

}
