package task1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenBrowserTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void MyFirstTest() {
        driver.get("http://software-testing.ru/");
        wait.until(ExpectedConditions.titleIs("Software-Testing.Ru"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
