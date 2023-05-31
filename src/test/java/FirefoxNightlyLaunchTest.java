import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FirefoxNightlyLaunchTest {

    private WebDriver driver;
    private WebDriverWait wait;



    @Test
    public void testLoginInFirefoxNightlyForExercise5() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.click();
        usernameInput.sendKeys("admin");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys("admin");

        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();

        driver.quit();
    }
}
