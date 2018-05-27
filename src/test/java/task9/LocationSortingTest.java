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

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}