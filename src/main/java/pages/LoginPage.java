package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMassage = By.tagName("h3");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }

    private void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    private void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public HomePage login(String username , String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new HomePage(driver);
    }

    public String getErrorMassage(){
        return driver.findElement(errorMassage).getText();
    }

    public HomePage successfulLogin(){
        enterUsername("standard_user");
        enterPassword("secret_sauce");
        clickLogin();
        return new HomePage(driver);
    }
}
