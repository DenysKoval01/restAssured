package com.restassured.regression.ui;

import static com.restassured.common.CommonMethods.properties;

import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;
import ui.pages.BookStorePage;
import ui.pages.LoginPage;
import ui.pages.MainPage;

public class LoginTest extends BaseUITest {

    @Test(description = "Login and verify User Name on Home Page")
    public void verifyUserNameOnHomePage() {
        MainPage mainPage = Selenide.open(properties.getProperty("base.url"),
                MainPage.class);
        mainPage.waitUntilPageIsLoaded();
        mainPage.clickOnElement(MainPage.BOOK_STORE_APPLICATION_MENU);

        MainPage.MainLoginPage mainLoginPage = new MainPage.MainLoginPage();
        mainLoginPage.clickOnElement(MainPage.MainLoginPage.LOGIN_BUTTON);
        LoginPage loginPage = new LoginPage();
        loginPage.setTextIntoElement(LoginPage.USER_NAME,properties.getProperty("login"));
        loginPage.setTextIntoElement(LoginPage.PASSWORD,properties.getProperty("password"));
        loginPage.clickOnElement(LoginPage.LOGIN);

        BookStorePage bookStorePage =new BookStorePage();
        String userNameAfterLogin = bookStorePage.getTextOfElement(BookStorePage.USER_NAME);

        Assert.assertEquals(userNameAfterLogin,properties.getProperty("login"));
    }
}
