package task19.tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import task19.pages.AddProductToCartPage;
import task19.pages.AllProductsPage;
import task19.pages.Page;
import task19.pages.RemoveProductFromCart;


public class AddRemoveToCartHighLevelTest {

    private WebDriver driver;

    private Page registrationPage;
    private AllProductsPage allProductsPage;
    private RemoveProductFromCart removeProductPage;
    private AddProductToCartPage addProductPage;

    public AddRemoveToCartHighLevelTest() {
        driver = new ChromeDriver();
        registrationPage = new Page(driver);
        allProductsPage = new AllProductsPage(driver);
        addProductPage = new AddProductToCartPage(driver);
        removeProductPage = new RemoveProductFromCart(driver);
    }

    public void quit() {
        driver.quit();
    }

    @Test
    public void addRemoveToCartTest() {
        allProductsPage.open();

        for (int i = 0; i < 3; i++) {
            allProductsPage.get1stProductFromPage();
            addProductPage.addProductToCart(i);
            addProductPage.backToAllProductPage();
        }

        allProductsPage.goToCheckoutPage();

        for (int i = 3; i > 0; i--) {
            //removeProductPage.removeProductFromCart(i);
        }
    }

    // @After
    public void stop() {
        quit();
        driver = null;
    }

}