package com.restassured.regression.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import listener.ExtendedRPListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners({ExtendedRPListener.class})
public class BaseUITest {

    @BeforeMethod
    public void beforeWholeTest() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
    }

    @AfterMethod
    public void tearDown(ITestResult testResult) {
        Selenide.closeWebDriver();
    }
}
