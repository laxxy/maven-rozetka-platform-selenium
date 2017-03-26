package com.rozetka.platform.selenium.pages;

import com.rozetka.platform.selenium.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest extends DriverFactory {
    static WebDriver webDriver;
    private final String baseUrl = System.getProperty("base.url");

    public static WebDriver getDriver() {
        return webDriver;
    }

    @BeforeSuite
    public void setUpSuite() {
        webDriver = getWebDriver();
        webDriver.manage().window().maximize();
    }

    /*@BeforeMethod
    public void setUp() throws Exception {
        webDriver.get(baseUrl);
    }*/

    @AfterSuite
    public void tearDown() throws Exception {
        if (webDriver != null) webDriver.quit();
    }
}