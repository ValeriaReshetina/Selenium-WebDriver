package ru.stqa.training.selenium.LitecartTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LitecartTests {

    private WebDriver driver;
    public WebDriverWait wait;
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
        for (Integer index : indexesOfNonZeroZonesElements) {
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

    @Test
    @DisplayName("Test for exercise 10)")
    public void testVerifyingThatOpensCorrectProductPage() {
        driver.get("http://localhost/litecart/en/");

    }

    @Test
    @DisplayName("Test for exercise 11)")
    public void testUserRegistration() {
        driver.get("http://localhost/litecart/en/create_account");
        String generatedMail = createRandomEmail() + "@mail.ru";

        driver.findElement(By.name("firstname")).sendKeys("Valeria");
        driver.findElement(By.name("lastname")).sendKeys("Reshetina");
        driver.findElement(By.name("address1")).sendKeys("Columbus Ave");
        driver.findElement(By.name("postcode")).sendKeys("01020");
        driver.findElement(By.name("city")).sendKeys("Springfield");
        driver.findElement(By.className("select2")).click();
        driver.findElement(By.className("select2-search__field")).sendKeys("United States" + Keys.ENTER);

        driver.findElement(By.name("email")).sendKeys(generatedMail);
        driver.findElement(By.name("phone")).sendKeys("+79881120310");
        driver.findElement(By.name("password")).sendKeys("q1w2e3r4t5y");
        driver.findElement(By.name("confirmed_password")).sendKeys("q1w2e3r4t5y");
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.xpath(
                "//div[@class = 'left']//a[@href = 'http://localhost/litecart/en/logout']")).click();

        driver.findElement(By.name("email")).sendKeys(generatedMail);
        driver.findElement(By.name("password")).sendKeys("q1w2e3r4t5y");
        driver.findElement(By.name("login")).click();
        driver.findElement(By.xpath(
                "//div[@class = 'left']//a[@href = 'http://localhost/litecart/en/logout']")).click();
    }

    @Test
    @DisplayName("Test for exercise 12)")
    public void addingNewProductTest() {
        driver.get("http://localhost/litecart/admin/");
        login("admin", "admin");
        String nameOfNewProduct = createRandomName(9);
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");

        //general tab
        driver.findElement(By.xpath("//*[contains(text(), 'Add New Product')]")).click();
        WebElement generalTab = driver.findElement(By.xpath("//*[contains(text(), 'General')]"));
        generalTab.findElement(By.xpath("//input[@name='status'][@value=1]")).click();
        generalTab.findElement(By.xpath("//*[@name='name[en]']")).sendKeys(nameOfNewProduct);
        generalTab.findElement(By.xpath("//*[@name='code']")).sendKeys(createRandomDigit(6));
        generalTab.findElement(By.xpath("//*[text()='Male']//parent::*//td/input")).click();
        File uploadFile = new File("src/test/resources/wildDuck.jpg");
        String filePath = uploadFile.getAbsolutePath();
        generalTab.findElement(By.xpath("//*[@name='new_images[]']")).sendKeys(filePath);
        generalTab.findElement(By.xpath("//*[@name='date_valid_from']")).sendKeys("23-06-2023");
        generalTab.findElement(By.xpath("//*[@name='date_valid_to']")).sendKeys("23-06-2025");

        //information tab
        driver.findElement(By.xpath("//*[contains(text(), 'Information')]")).click();
        WebElement infoTab = driver.findElement(By.xpath("//div[@id='tab-information']"));
        Select selectManufacturer = new Select(infoTab.findElement(By.xpath("//*[@name='manufacturer_id']")));
        selectManufacturer.selectByVisibleText("ACME Corp.");
        infoTab.findElement(By.xpath("//*[@name='keywords']")).sendKeys(createRandomName(7));
        infoTab.findElement(By.xpath("//*[@name='short_description[en]']"))
                .sendKeys(createRandomName(7));
        infoTab.findElement(By.xpath("//div[@class='trumbowyg-editor']"))
                .sendKeys(createRandomName(5) + " " + createRandomName(7));
        infoTab.findElement(By.xpath("//*[@name='head_title[en]']")).sendKeys(createRandomName(5));
        infoTab.findElement(By.xpath("//*[@name='meta_description[en]']"))
                .sendKeys(createRandomName(4));

        //prices tab
        driver.findElement(By.xpath("//a[text()='Prices']")).click();
        WebElement pricesTab = driver.findElement(By.xpath("//div[@id='tab-prices']"));
        pricesTab.findElement(By.xpath("//*[@name='purchase_price']"))
                .sendKeys(Keys.CONTROL + "A");
        pricesTab.findElement(By.xpath("//*[@name='purchase_price']"))
                .sendKeys(createRandomDigit(2) + "," + createRandomDigit(2));
        Select selectCurrency = new Select(pricesTab.findElement(
                By.xpath("//*[@name='purchase_price_currency_code']")));
        selectCurrency.selectByValue("USD");
        pricesTab.findElement(By.xpath("//*[@name='prices[USD]']"))
                .sendKeys(createRandomDigit(2) + "," + createRandomDigit(2));
        driver.findElement(By.xpath("//*[@name='save']")).click();

        WebElement searchInput = driver.findElement(By.xpath("//*[@name='query']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(searchInput).click().sendKeys(nameOfNewProduct).sendKeys(Keys.ENTER).perform();
    }

    protected String createRandomName(int length) {
        Random rnd = new Random();
        return rnd.ints(65, 122)
                .filter(i -> (i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    protected String createRandomDigit(int length) {
        Random rnd = new Random();
        return rnd.ints(48, 57)
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    protected String createRandomEmail() {
        String saltChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        return salt.toString();
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
