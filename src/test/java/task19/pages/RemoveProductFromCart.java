package task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RemoveProductFromCart extends Page {

    public RemoveProductFromCart(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void removeProductFromCart(final int k) {
        driver.findElement(By.name("remove_cart_item")).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElements(By.cssSelector("td.item")).size() == (k - 1);
            }
        });
    }

}

