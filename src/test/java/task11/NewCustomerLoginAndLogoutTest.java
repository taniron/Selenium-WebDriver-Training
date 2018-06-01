package task11;

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


public class NewCustomerLoginAndLogoutTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void newCustomerLoginAndLogoutTest() {
        //go to litecart user part
        driver.get("http://localhost/litecart");
        wait.until(ExpectedConditions.titleIs("Online Store | My Store"));

        //create account
        driver.findElement(By.cssSelector("div.content table td a")).click();
        wait.until(ExpectedConditions.titleIs("Create Account | My Store"));

        driver.findElement(By.name("firstname")).sendKeys("Peter", Keys.TAB, "Smith", Keys.TAB, "5th Avenu",
                Keys.TAB, Keys.TAB, "12345", Keys.TAB, "Boston", Keys.TAB);
        final WebElement countrySelectWebElement = driver.findElement(By.name("country_code"));
        Select dropdown = new Select(countrySelectWebElement);
        dropdown.selectByVisibleText("United States");

        final String userEmail = System.currentTimeMillis() + "@gmail.com";
        final String userPassword = "a12345";
        driver.findElement(By.name("email")).sendKeys(userEmail, Keys.TAB, "45896325",
                Keys.TAB, Keys.TAB, userPassword, Keys.TAB, userPassword, Keys.TAB, Keys.ENTER);
        wait.until(ExpectedConditions.titleIs("Online Store | My Store"));

        //logout
        driver.findElement(By.linkText("Logout")).click();

        //login
        driver.findElement(By.name("email")).sendKeys(userEmail, Keys.TAB, userPassword, Keys.TAB, Keys.TAB, Keys.ENTER);

        //logout
        driver.findElement(By.linkText("Logout")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}