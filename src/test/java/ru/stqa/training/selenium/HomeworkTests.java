package ru.stqa.training.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;


public class HomeworkTests {

    private WebDriver driver;
    private WebDriverWait wait;
    String browser = "chrome";

    @BeforeEach
    public void start() {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("IE")) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testForExercise1() throws InterruptedException {
        driver.get("http://www.bing.com");
        driver.get("http://www.google.com");
        Thread.sleep(500);
    }

    @Test
    public void testLoginForExercise3() {
        driver.get("http://localhost/litecart/admin/");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.click();
        usernameInput.sendKeys("admin");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys("admin");

        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
    }

    @AfterEach
    public void stop() {
         driver.quit();
         driver = null;
    }
}
