package login;

import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTests {
    @Test
    public void testSuccessfulLogin(){
        loginPage.login("standard_user" , "secret_sauce");
    }

    @Test
    public void testLockedOutUserLogin(){
        loginPage.login("locked_out_user" , "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMassage() ,
                "Epic sadface: Sorry, this user has been locked out." ,
                "Incorrect Massage");
    }




}
