package task8;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class StickerExistenceTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void stickerExistenceTest() {
        //go to Store home page
        driver.get("http://localhost/litecart");
        wait.until(ExpectedConditions.titleIs("Online Store | My Store"));

        //go over all products and check stickers count
        for (int i = 0; i < driver.findElements(By.cssSelector(".product")).size(); i++) {
            final WebElement productElement = driver.findElements(By.cssSelector(".product")).get(i);
            final int stickersCount = productElement.findElements(By.cssSelector(".sticker")).size();
            assertEquals("Count of product stickers different from expected", 1, stickersCount);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}