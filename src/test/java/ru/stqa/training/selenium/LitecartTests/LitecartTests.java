package ru.stqa.training.selenium.LitecartTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        //Appearance
        WebElement appearanceMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Appearence')]"));
        appearanceMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Template')]")));

        WebElement appearanceSubMenuLogotype = driver.findElement(
                By.xpath("//*[contains(text(), 'Logotype')]"));
        appearanceSubMenuLogotype.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Logotype')]")));

        //Catalog
        WebElement catalogMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Catalog')]"));
        catalogMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Catalog')]")));

        WebElement catalogSubMenuProductGroups = driver.findElement(
                By.xpath("//*[contains(text(), 'Product Groups')]"));
        catalogSubMenuProductGroups.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Product Groups')]")));

        WebElement catalogSubMenuOptionGroups = driver.findElement(
                By.xpath("//*[contains(text(), 'Option Groups')]"));
        catalogSubMenuOptionGroups.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Option Groups')]")));

        WebElement catalogSubMenuManufacturers = driver.findElement(
                By.xpath("//*[contains(text(), 'Manufacturers')]"));
        catalogSubMenuManufacturers.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Manufacturers')]")));

        WebElement catalogSubMenuSuppliers = driver.findElement(
                By.xpath("//*[contains(text(), 'Suppliers')]"));
        catalogSubMenuSuppliers.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Suppliers')]")));

        WebElement catalogSubMenuDeliveryStatuses = driver.findElement(
                By.xpath("//*[contains(text(), 'Delivery Statuses')]"));
        catalogSubMenuDeliveryStatuses.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Delivery Statuses')]")));

        WebElement catalogSubMenuSoldOutStatuses = driver.findElement(
                By.xpath("//*[contains(text(), 'Sold Out Statuses')]"));
        catalogSubMenuSoldOutStatuses.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Sold Out Statuses')]")));

        WebElement catalogSubMenuQuantityUnits = driver.findElement(
                By.xpath("//*[contains(text(), 'Quantity Units')]"));
        catalogSubMenuQuantityUnits.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Quantity Units')]")));

        WebElement catalogSubMenuCSVImportExport = driver.findElement(
                By.xpath("//*[contains(text(), 'CSV Import/Export')]"));
        catalogSubMenuCSVImportExport.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'CSV Import/Export')]")));

        //Countries
        WebElement countriesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Countries')]"));
        countriesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Countries')]")));

        //Currencies
        WebElement currenciesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Currencies')]"));
        currenciesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Currencies')]")));

        //Customers
        WebElement customersMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Customers')]"));
        customersMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Customers')]")));

        WebElement customersSubMenuCSVImportExport = driver.findElement(
                By.xpath("//*[contains(text(), 'CSV Import/Export')]"));
        customersSubMenuCSVImportExport.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'CSV Import/Export')]")));

        WebElement customersSubMenuNewsletter = driver.findElement(
                By.xpath("//*[contains(text(), 'Newsletter')]"));
        customersSubMenuNewsletter.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Newsletter')]")));

        //Geo Zones
        WebElement geoZonesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Geo Zones')]"));
        geoZonesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Geo Zones')]")));

        //Languages
        WebElement languagesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Languages')]"));
        languagesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Languages')]")));

        WebElement languagesSubMenuStorageEncoding = driver.findElement(
                By.xpath("//*[contains(text(), 'Storage Encoding')]"));
        languagesSubMenuStorageEncoding.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Storage Encoding')]")));

        //Modules
        WebElement modulesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Modules')]"));
        modulesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Job Modules')]")));

        WebElement modulesSubMenuCustomer = driver.findElement(
                By.xpath("//*[text()='Customer']"));
        modulesSubMenuCustomer.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Customer Modules')]")));

        WebElement modulesSubMenuShipping = driver.findElement(
                By.xpath("//*[contains(text(), 'Shipping')]"));
        modulesSubMenuShipping.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Shipping Modules')]")));

        WebElement modulesSubMenuPayment = driver.findElement(
                By.xpath("//*[contains(text(), 'Payment')]"));
        modulesSubMenuPayment.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Payment Modules')]")));

        WebElement modulesSubMenuOrderTotal = driver.findElement(
                By.xpath("//*[contains(text(), 'Order Total')]"));
        modulesSubMenuOrderTotal.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Order Total Modules')]")));

        WebElement modulesSubMenuOrderSuccess = driver.findElement(
                By.xpath("//*[contains(text(), 'Order Success')]"));
        modulesSubMenuOrderSuccess.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Order Success Modules')]")));

        WebElement modulesSubMenuOrderAction = driver.findElement(
                By.xpath("//*[contains(text(), 'Order Action')]"));
        modulesSubMenuOrderAction.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Order Action Modules')]")));

        //Orders
        WebElement ordersMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Orders')]"));
        ordersMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Orders')]")));

        WebElement ordersSubMenuOrderStatuses = driver.findElement(
                By.xpath("//*[contains(text(), 'Order Statuses')]"));
        ordersSubMenuOrderStatuses.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Order Statuses')]")));

        //Pages
        WebElement pagesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Pages')]"));
        pagesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Pages')]")));

        //Reports
        WebElement reportsMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Reports')]"));
        reportsMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Monthly Sales')]")));

        WebElement reportsSubMenuMostSoldProducts = driver.findElement(
                By.xpath("//*[contains(text(), 'Most Sold Products')]"));
        reportsSubMenuMostSoldProducts.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Most Sold Products')]")));

        WebElement reportsSubMenuMostShoppingCustomers = driver.findElement(
                By.xpath("//*[contains(text(), 'Most Shopping Customers')]"));
        reportsSubMenuMostShoppingCustomers.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Most Shopping Customers')]")));

        //Settings
        WebElement settingsMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Settings')]"));
        settingsMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Settings')]")));

        WebElement settingsSubMenuDefaults = driver.findElement(
                By.xpath("//*[contains(text(), 'Defaults')]"));
        settingsSubMenuDefaults.click();

        WebElement settingsSubMenuGeneral = driver.findElement(
                By.xpath("//*[contains(text(), 'General')]"));
        settingsSubMenuGeneral.click();

        WebElement settingsSubMenuListings = driver.findElement(
                By.xpath("//*[contains(text(), 'Listings')]"));
        settingsSubMenuListings.click();

        WebElement settingsSubMenuImages = driver.findElement(
                By.xpath("//*[contains(text(), 'Images')]"));
        settingsSubMenuImages.click();

        WebElement settingsSubMenuCheckout = driver.findElement(
                By.xpath("//*[contains(text(), 'Checkout')]"));
        settingsSubMenuCheckout.click();

        WebElement settingsSubMenuAdvanced = driver.findElement(
                By.xpath("//*[contains(text(), 'Advanced')]"));
        settingsSubMenuAdvanced.click();

        WebElement settingsSubMenuSecurity = driver.findElement(
                By.xpath("//*[contains(text(), 'Security')]"));
        settingsSubMenuSecurity.click();

        //Slides
        WebElement slidesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Slides')]"));
        slidesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Slides')]")));

        //Tax
        WebElement taxMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Tax')]"));
        taxMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Tax Classes')]")));

        WebElement taxSubMenuRates = driver.findElement(
                By.xpath("//*[contains(text(), 'Tax Rates')]"));
        taxSubMenuRates.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Tax Rates')]")));

        //Translations
        WebElement translationsMenuItem = driver.findElement(
                By.xpath("//*[contains(text(), 'Translations')]"));
        translationsMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Search Translations')]")));

        WebElement translationsSubMenuScanFiles = driver.findElement(
                By.xpath("//*[text()='Scan Files']"));
        translationsSubMenuScanFiles.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Scan Files For Translations')]")));

        WebElement translationsSubMenuCSVImportExport = driver.findElement(
                By.xpath("//*[contains(text(), 'CSV Import/Export')]"));
        translationsSubMenuCSVImportExport.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'CSV Import/Export')]")));

        //Users
        WebElement usersMenuItem = driver.findElement(
                By.xpath("//*[contains(text(), 'Users')]"));
        usersMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Users')]")));

        //vQmods
        WebElement vQmodsMenuItem = driver.findElement(
                By.xpath("//*[contains(text(), 'vQmods')]"));
        vQmodsMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'vQmods')]")));
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
    @DisplayName("Test for exercise 8")
    public void testCheckingForCountriesAndGeofencesOnCountriesPage() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");

        //Countries
        WebElement countriesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Countries')]"));
        countriesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Countries')]")));

        //Geo Zones
        WebElement geoZonesMenuItem = driver.findElement(By.xpath("//*[contains(text(), 'Geo Zones')]"));
        geoZonesMenuItem.click();
        assertTrue(isElementPresent(By.xpath("//h1[contains(text(), 'Geo Zones')]")));
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
