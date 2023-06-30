package ru.stqa.training.selenium.PageObject.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import ru.stqa.training.selenium.PageObject.pages.CartPage;
import ru.stqa.training.selenium.PageObject.pages.MainPage;
import ru.stqa.training.selenium.PageObject.pages.ProductPage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AddAndDeleteCartTest {

    private WebDriver driver;
    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;
    String browser = "FIREFOX";

    @BeforeEach
    public void start() {
        if (browser.equalsIgnoreCase("CHROME")) {
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("IE")) {
            driver = new InternetExplorerDriver();
        }
        if (browser.equalsIgnoreCase("FIREFOX")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Mozilla Firefox\\firefox.exe")));
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void cartTest() {
        mainPage.open();
        for (int i = 0; i < 3; i++) {
            mainPage.clickOnPlate();
            productPage.inputSize();
            productPage.addProductToCart();
            productPage.waitCountProductOfCart(i);
            productPage.backToMainPage();
        }
        mainPage.openCart();
        int countRow = cartPage.getCountRow();
        int countProduct = cartPage.getCountProduct();
        for (int i = 0; i < countProduct; i++) {
            if (i != countProduct - 1) {
                cartPage.removeProduct();
                Assertions.assertTrue(cartPage.getCountRow() < countRow);
                countRow = cartPage.getCountRow();
            } else {
                cartPage.removeLastProduct();
                cartPage.assertCartIsEmpty();
            }
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
