package task19.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddProductToCartPage extends Page {

    public AddProductToCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart(final int k) {
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
    }

    public void backToAllProductPage() {
        driver.findElement(By.cssSelector(".fa")).click();
    }

}
