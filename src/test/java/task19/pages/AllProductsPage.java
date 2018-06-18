package task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllProductsPage extends Page {

    public AllProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public AllProductsPage open() {
        driver.get("http://localhost/litecart");
        wait.until(ExpectedConditions.titleIs("Online Store | My Store"));
        return this;
    }

    public void get1stProductFromPage() {
        driver.findElement(By.cssSelector("li.product a.link")).click();

    }

    public void goToCheckoutPage() {
        driver.findElement(By.linkText("Checkout »")).click();
    }
}