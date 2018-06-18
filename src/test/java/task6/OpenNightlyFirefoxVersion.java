package task6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class OpenNightlyFirefoxVersion {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("C:\\Program Files\\Firefox Nightly\\Firefox.exe")));
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest() {

        //go to required url
        driver.get("http://localhost/litecart/admin/");
        wait.until(ExpectedConditions.titleIs("My Store"));

        //login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));

        //go to shop
        driver.findElement(By.xpath("//a[@title=\"Catalog\"]")).click();

        //pick an yellow duck
        driver.findElement(By.xpath("//a[@title=\"Yellow Duck\"]")).click();
        driver.findElement(By.name("options[Size]")).click();
        driver.findElement(By.xpath("//option[@value=\"Medium\"]")).click();
        driver.findElement(By.name("add_cart_product")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}