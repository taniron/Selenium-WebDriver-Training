package task13;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddRemoveToCartTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void addRemoveToCartTest() {
        //go to required url
        driver.get("http://localhost/litecart");
        wait.until(ExpectedConditions.titleIs("Online Store | My Store"));

        //go to Catalog and get 1st product 3 times
        for (int i = 0; i < 3; i++) {
            final int k = i;
            driver.findElement(By.cssSelector("li.product a.link")).click();

            if (driver.findElements(By.name("options[Size]")).size() > 0) {
                Select sizeSelectElement = new Select(driver.findElement(By.name("options[Size]")));
                sizeSelectElement.selectByVisibleText("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();

            //wait not more than 10 seconds until product appears in the cart
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElement(By.cssSelector(".quantity")).getText().startsWith(Integer.toString(k + 1));
                }
            });

            driver.findElement(By.cssSelector(".fa")).click();
        }
        //go to Checkout
        driver.findElement(By.linkText("Checkout »")).click();

        //Remove all products from the cart
        for (int i = 3; i > 0; i--) {
            final int k = i;
            driver.findElement(By.name("remove_cart_item")).click();

            //Wait not more than 10 seconds until product will be removed from the cart
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.findElements(By.cssSelector("td.item")).size() == (k - 1);
                }
            });
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
