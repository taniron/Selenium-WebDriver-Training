package task4;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.ie.InternetExplorerDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowsersRunner {

    private  WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void chromeLoginTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        loginTest(driver,wait);
    }

    @Test
    public void ieLoginTest() {

        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("ignoreZoomSetting", true);
        driver = new InternetExplorerDriver(new InternetExplorerOptions(caps));
        wait = new WebDriverWait(driver, 10);
        loginTest(driver,wait);
    }

    @Test
    public void firefoxLoginTest() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        loginTest(driver,wait);
    }

    private void loginTest(WebDriver driver, WebDriverWait wait) {
        //go to required url
        driver.get("http://localhost/litecart/admin/");
        this.wait.until(ExpectedConditions.titleIs("My Store"));

        //login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        this.wait.until(ExpectedConditions.titleIs("My Store"));

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
        if (driver!=null){
            driver.quit();
            driver = null;
        }
    }

}