package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class SideMenu {
    private WebDriver driver;
    private By menuButton = By.xpath("/html/body/div/div[1]/div/div[3]/div/button");
    private By selectAllItems = By.linkText("All Items");
    private By logout = By.linkText("Logout");
    private By about = By.linkText("About");
    private By reset = By.linkText("Reset App State");

    public SideMenu(WebDriver driver){
        this.driver = driver;
    }

    public HomePage clickAllItems(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(selectAllItems));
        driver.findElement(selectAllItems).click();
        return new HomePage(driver);
    }

    public void clickAbout(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(about));
        driver.findElement(about).click();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public LoginPage clickLogout(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(logout));
        driver.findElement(logout).click();
        return new LoginPage(driver);
    }

    public boolean isDisplayed(By locator){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator).isDisplayed();
    }

    public void clickResetAppState(){
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(10));
        driver.findElement(menuButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(reset));
        driver.findElement(reset).click();


    }
}
