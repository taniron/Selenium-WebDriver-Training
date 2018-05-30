package task9;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class LocationSortingTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        //perform login before the tests
        driver.get("http://localhost/litecart/admin/");
        wait.until(ExpectedConditions.titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));
    }

    @Test
    public void countriesSortingTest() {
        //go to countries page
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(ExpectedConditions.titleIs("Countries | My Store"));

        List<WebElement> columnsHeaders = driver.findElements(By.cssSelector("table tr th"));
        int columnNameIndex = -1;
        for (WebElement columnHeader : columnsHeaders) {
            if (columnHeader.getText().compareTo("Name") == 0) {
                columnNameIndex = columnsHeaders.indexOf(columnHeader);
            }
        }
        assertTrue("Countries 'Name' column not found", columnNameIndex >= 0);

        //fetching countries column
        List<WebElement> countriesWebElements = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[" + (columnNameIndex + 1) + "]"));

        //check if countries are properly sorted
        for (int i = 1; i < countriesWebElements.size(); i++) {
            String currentCountry = countriesWebElements.get(i).getText();
            String previousCountry = countriesWebElements.get(i - 1).getText();
            assertTrue("Countries are not properly sorted", previousCountry.compareTo(currentCountry) < 0);
        }
    }

    @Test
    public void zonesOfCountriesSortingTest() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(ExpectedConditions.titleIs("Countries | My Store"));

        List<WebElement> columnsHeaders = driver.findElements(By.cssSelector("table tr th"));
        int columnZoneIndex = -1;
        int columnNameIndex = -1;
        for (WebElement columnHeader : columnsHeaders) {
            if (columnHeader.getText().compareTo("Zones") == 0) {
                columnZoneIndex = columnsHeaders.indexOf(columnHeader);
            }
            if (columnHeader.getText().compareTo("Name") == 0) {
                columnNameIndex = columnsHeaders.indexOf(columnHeader);
            }
        }
        assertTrue("Countries 'Zone' column not found", columnZoneIndex >= 0);

        //check if countries are properly sorted
        int rowWithNotZeroZonesIndex = -1;
        final String zoneColumnXpath = "//*[@id=\"content\"]/form/table/tbody/tr/td[" + (columnZoneIndex + 1) + "]";
        for (int i = 1; i < driver.findElements(By.xpath(zoneColumnXpath)).size(); i++) {
            if (driver.findElements(By.xpath(zoneColumnXpath)).get(i).getText().compareTo("0") != 0) {
                rowWithNotZeroZonesIndex = driver.findElements(By.xpath(zoneColumnXpath)).indexOf(driver.findElements(By.xpath(zoneColumnXpath)).get(i));
                driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + (rowWithNotZeroZonesIndex + 2) + "]/td[" + (columnNameIndex + 1) + "]/a")).click();

                columnsHeaders = driver.findElements(By.cssSelector("table tr th"));
                int columnNameIndex1 = -1;
                for (WebElement columnHeader : columnsHeaders) {
                    if (columnHeader.getText().compareTo("Name") == 0) {
                        columnNameIndex1 = columnsHeaders.indexOf(columnHeader);
                    }
                }
                assertTrue("Countries 'Name' column not found", columnNameIndex1 >= 0);

                //fetching countries column
                List<WebElement> zonesOfCountriesWebElements = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[" + (columnNameIndex + 1) + "]"));

                //check if countries are properly sorted
                for (int j = 1; j < zonesOfCountriesWebElements.size(); j++) {
                    String currentCountry = zonesOfCountriesWebElements.get(j).getText();
                    String previousCountry = zonesOfCountriesWebElements.get(j - 1).getText();
                    assertTrue("Zones are not properly sorted", previousCountry.compareTo(currentCountry) < 0);
                }
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                wait.until(ExpectedConditions.titleIs("Countries | My Store"));
            }
        }
    }

    @Test
    public void zonesSortingTest() {
        //go to countries page
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        wait.until(ExpectedConditions.titleIs("Geo Zones | My Store"));

        for (int i = 0; i < driver.findElements(By.cssSelector("td#content form table.dataTable tr.row td:nth-child(3) a")).size(); i++) {
            driver.findElements(By.cssSelector("td#content form table.dataTable tr.row td:nth-child(3) a")).get(i).click();
            wait.until(ExpectedConditions.titleIs("Edit Geo Zone | My Store"));

            List<WebElement> columnsHeaders = driver.findElements(By.cssSelector("table#table-zones tr th"));
            int columnZoneIndex = -1;

            for (WebElement columnHeader : columnsHeaders) {
                if (columnHeader.getText().compareTo("Zone") == 0) {
                    columnZoneIndex = columnsHeaders.indexOf(columnHeader);
                }
            }
            assertTrue("'Zone' column not found", columnZoneIndex >= 0);

            //fetching zone column
            List<WebElement> zoneWebElements = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[" + (columnZoneIndex + 1) + "]/select/option[@selected]"));
            //check if zones are properly sorted
            for (int j = 1; j < zoneWebElements.size(); j++) {
                String currentZone = zoneWebElements.get(j).getText();
                String previousZone = zoneWebElements.get(j - 1).getText();
                assertTrue("Zones are not properly sorted", previousZone.compareTo(currentZone) < 0);
            }
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            wait.until(ExpectedConditions.titleIs("Geo Zones | My Store"));
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
