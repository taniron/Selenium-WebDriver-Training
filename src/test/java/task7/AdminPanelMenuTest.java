package task7;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPanelMenuTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void adminPanelMenuTest() {

        //go to required url
        driver.get("http://localhost/litecart/admin/");
        wait.until(ExpectedConditions.titleIs("My Store"));

        //login
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.titleIs("My Store"));

        //go over list menu
        for (int i = 0; i < driver.findElements(By.id("app-")).size(); i++) {
            driver.findElements(By.id("app-")).get(i).click();
            //validate if title (h1) exists
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("h1"))));
            //go over each child element of left menu
            for (int j = 0; j < driver.findElements(By.id("app-")).get(i).findElements(By.tagName("li")).size(); j++) {
                driver.findElements(By.id("app-")).get(i).findElements(By.tagName("li")).get(j).click();
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}


