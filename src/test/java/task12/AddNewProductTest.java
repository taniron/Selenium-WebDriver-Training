package task12;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class AddNewProductTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addNewProductTest() {
        //go to required url
        driver.get("http://localhost/litecart/admin/");
        wait.until(ExpectedConditions.titleIs("My Store"));

        //login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));

        //go to Catalog
        driver.findElement(By.linkText("Catalog")).click();
        wait.until(ExpectedConditions.titleIs("Catalog | My Store"));

        //add new product
        driver.findElement(By.linkText("Add New Product")).click();
        wait.until(ExpectedConditions.titleIs("Add New Product | My Store"));

        //enable the product
        final List<WebElement> statusElements = driver.findElements(By.name("status"));
        for (WebElement statusElement : statusElements) {
            if (statusElement.findElement(By.xpath("./..")).getText().compareTo("Enabled") == 0) {
                statusElement.click();
                break;
            }
        }
        driver.findElement(By.name("name[en]")).sendKeys("Golden Duck", Keys.TAB, "RD009");

        driver.findElement(By.xpath("//input[@data-name=\"Root\"]")).click();
        driver.findElement(By.xpath("//input[@data-name=\"Rubber Ducks\"]")).click();
        driver.findElement(By.xpath("//input[@value=\"1-3\"]")).click();

        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("4", Keys.TAB, Keys.TAB);

        File newProductImageFile = new File("src/test/resources/golden_duck.jpg");
        WebElement inputFileElement = driver.findElement(By.xpath("//input[@type='file']"));
        inputFileElement.sendKeys(newProductImageFile.getAbsolutePath());


        final LocalDate today = LocalDate.now();
        driver.findElement(By.name("date_valid_from")).sendKeys(today.format(DateTimeFormatter.ofPattern("ddMMM\tyyyy")));
        driver.findElement(By.name("date_valid_to")).sendKeys(today.plusYears(1).format(DateTimeFormatter.ofPattern("ddMMM\tyyyy")));

        //go to Information Tab
        driver.findElement(By.linkText("Information")).click();
        //wait for Information Tab to become visible
        Select manufacturerSelectElement = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("manufacturer_id"))));
        manufacturerSelectElement.selectByVisibleText("ACME Corp.");

        driver.findElement(By.name("keywords")).sendKeys("gold golden duck", Keys.TAB, "Here comes short description", Keys.TAB, "Here comes long description", Keys.TAB, "Golden Duck", Keys.TAB, "Golden Duck");

        //go to Prices Tab
        driver.findElement(By.linkText("Prices")).click();

        //wait for Prices Tab to become visible
        WebElement purchasePrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("purchase_price")));
        purchasePrice.clear();
        purchasePrice.sendKeys("25.00");

        Select currencySelectElement = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        currencySelectElement.selectByVisibleText("US Dollars");

        driver.findElement(By.name("prices[USD]")).sendKeys("25.00");
        driver.findElement(By.name("save")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
