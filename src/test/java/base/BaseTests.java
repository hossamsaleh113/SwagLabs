package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import utils.CartIcon;
import utils.SideMenu;

import java.util.HashMap;
import java.util.Map;

public class BaseTests {
    private WebDriver driver;
    protected LoginPage loginPage;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver(getChromeOptions());
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    public void goToLoginPage(){
        driver.get("https://www.saucedemo.com/v1/");
    }

    @AfterTest
    public void wrapUp(){
        driver.quit();
    }


    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        String userDataDir = System.getProperty("user.home") + "\\SeleniumChromeProfile";
        options.addArguments("--user-data-dir=" + userDataDir);

        return options;
    }

}


