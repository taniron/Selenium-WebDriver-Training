package task3;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
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


