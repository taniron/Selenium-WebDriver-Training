package task17;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class BrowserLogTest {


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
    public void browserLogTest() {
        //go to catalog page
        driver.findElement(By.linkText("Catalog")).click();
        wait.until(ExpectedConditions.titleIs("Catalog | My Store"));
        driver.findElement(By.linkText("Rubber Ducks")).click();

        for (int i = 1; i < driver.findElements(By.partialLinkText("Duck")).size(); i++) {
            //go to product page
            driver.findElements(By.partialLinkText("Duck")).get(i).click();
            //check that browser has no log messages
            assertTrue("Browser logs has message", driver.manage().logs().get("browser").getAll().size() == 0);

            //go back
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.history.go(-1)");
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

