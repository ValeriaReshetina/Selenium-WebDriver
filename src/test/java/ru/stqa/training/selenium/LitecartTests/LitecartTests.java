package ru.stqa.training.selenium.LitecartTests;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LitecartTests {

    private WebDriver driver;
    public WebDriverWait wait;
    String browser = "CHROME";

    @BeforeEach
    public void start() {
        if (browser.equalsIgnoreCase("CHROME")) {
            driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("IE")) {
            driver = new InternetExplorerDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Test for exercise 3")
    public void testAdminLogin() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
    }

    @Test
    @DisplayName("Test for exercise 6")
    public void testScriptThroughAllAdminPanelSections() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");

        List<WebElement> leftMenuItems = driver.findElements(By.className("name"));
        List<String> leftMenuItemNames = getElementNames(leftMenuItems);

        for (String s1 : leftMenuItemNames) {
            driver.findElement(By.xpath("//span[text()='" + s1 + "']")).click();
            assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

            List<WebElement> menuSubItems = driver.findElements(By.cssSelector(("[id^=doc-]")));
            List<String> leftSubMenuItemNames = getElementNames(menuSubItems);

            for (String s2 : leftSubMenuItemNames) {
                driver.findElement(By.xpath("//span[text()='" + s2 + "']")).click();
                assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());
            }
        }
    }
    private List<String> getElementNames(List<WebElement> elements) {
        List<String> elementNames = new ArrayList<String>();
        for (WebElement e : elements) {
            elementNames.add(e.getText());
        }
        return elementNames;
    }

    @Test
    @DisplayName("Test for exercise 7")
    public void testCheckingForStickers() {
        driver.get("http://localhost/litecart/en/");

        List<WebElement> allDucksProductCardList = driver.findElements(
                By.xpath("//*[contains(@class, 'product column')]"));

        Integer allProductsAmount = allDucksProductCardList.size();

        for (int i = 1; i <= allProductsAmount; i++) {
            List<WebElement> stickerElements = driver.findElements(By.xpath(
                    "(//*[contains(@class, 'product column')])[" +
                            i + "]" + "//*[contains(@class, 'sticker')]"));
            Assertions.assertEquals(stickerElements.size(), 1,
                    "Error: amount of product stickers not equal 1");
        }
    }

    @Test
    @DisplayName("Test for exercise 8)")
    public void testCheckingSortingOfCountriesAndGeofencesOnCountriesPage() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");

        //a) checking if countries are in alphabetical order
        WebElement countriesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Countries')]"));
        countriesMenuItem.click();

        List<WebElement> countryNameElements = driver.findElements(By.xpath(
                "//*[@class='dataTable']//a[contains(@href, 'edit_country') and not(contains(@title,'Edit'))]"));
        List<String> countryNames = new ArrayList<>();
        for (WebElement countryName : countryNameElements) {
            countryNames.add(countryName.getText());
        }
        ArrayList<String> sortedCopyOfCountryNames = new ArrayList<>(countryNames);
        Collections.sort(sortedCopyOfCountryNames);

        Assertions.assertEquals(countryNames, sortedCopyOfCountryNames);

        //b) checking if zones are in alphabetical order for countries with more than one zone
        List<WebElement> zoneCounterElements = driver.findElements(
                By.xpath("//*[@class='row']//td[6]"));
        List<Integer> indexesOfNonZeroZonesElements = new ArrayList<>();

        for (int i = 0; i < zoneCounterElements.size(); i++) {
            WebElement zoneWebElement = zoneCounterElements.get(i);
            String text = zoneWebElement.getText();

            if (!text.equalsIgnoreCase("0")) {
                indexesOfNonZeroZonesElements.add(i + 1);
            }
        }
        for (Integer index : indexesOfNonZeroZonesElements){
            String xpathOfEditButton = "(//*[@class='row']//td[7])[" + index + "]";
            driver.findElement(By.xpath(xpathOfEditButton)).click();

            List<WebElement> zoneNameElements = driver.findElements(By.xpath(
                    "//*[@id='table-zones']//tr//td[3]/input[not(contains(@data-size,'medium'))]/parent::*"));
            List<String> zoneNames = new ArrayList<>();
            for (WebElement zoneName : zoneNameElements) {
                zoneNames.add(zoneName.getText());
            }
            ArrayList<String> sortedCopyOfZoneNames = new ArrayList<>(zoneNames);
            Collections.sort(sortedCopyOfZoneNames);

            Assertions.assertEquals(zoneNames, sortedCopyOfZoneNames);
            driver.navigate().back();
        }
    }

    @Test
    @DisplayName("Test for exercise 9)")
    public void testCheckingGeofenceSortingOnGeofencesPage() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        WebElement geoZonesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Geo Zones')]"));
        geoZonesMenuItem.click();

        WebElement tableZones = driver.findElement(By.xpath("//*[@class='dataTable']"));
        List<WebElement> zoneRows = tableZones.findElements(By.xpath("//*[@class='row']"));
        for (int i = 0; i < zoneRows.size(); i++) {
            zoneRows.get(i).findElement(By.cssSelector("td:nth-child(3)>a")).click();
            WebElement tableCountryZones = driver.findElement(By.xpath("//*[@id='table-zones']"));
            List<WebElement> stateRows = tableCountryZones.findElements(
                    By.cssSelector("tr:not(.header):not(:last-child)"));
            ArrayList<String> zoneList = new ArrayList<>();
            for (WebElement stateRow : stateRows) {
                zoneList.add(stateRow.findElement(By.cssSelector(
                        "td:nth-child(3)>select>option[selected=selected]")).getAttribute("textContent"));
            }
            List<String> sortedList = new ArrayList(zoneList);
            Collections.sort(sortedList);

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            zoneRows = driver.findElements(By.xpath("//*[@class='dataTable']//*[@class='row']"));

            Assertions.assertEquals(zoneList, sortedList);
        }
    }

    private void login(String username, String password) {
        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.click();
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys(password);

        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
    }

    protected boolean isElementPresent(By locator) {
        try {
            //wait.until((WebDriver driver) -> driver.findElement(locator));
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
