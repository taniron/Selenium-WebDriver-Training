package task10;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CorrectProductPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    public void chromeProductPageTest() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        correctProductPageTest(driver, wait);
    }

    @Test
    public void ieProductPageTest() {

        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability("ignoreZoomSetting", true);
        driver = new InternetExplorerDriver(new InternetExplorerOptions(caps));
        wait = new WebDriverWait(driver, 10);
        correctProductPageTest(driver, wait);
    }

    @Test
    public void firefoxProductPage() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        correctProductPageTest(driver, wait);
    }


    public void correctProductPageTest(WebDriver driver, WebDriverWait wait) {
        //go to Store home page
        driver.get("http://localhost/litecart");
        wait.until(ExpectedConditions.titleIs("Online Store | My Store"));


        String nameOfProduct = driver.findElement(By.cssSelector("div#box-campaigns div.name")).getText();
        String regularPrice = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s.regular-price")).getText();
        String campaignPrice = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong.campaign-price")).getText();

        //check grey color of regular price
        String regularPriceColor = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s.regular-price")).getCssValue("color");
        String[] rgbaStringArray = regularPriceColor.replaceAll("rgb\\(|rgba\\(|\\)| ", "").split(",");
        int red = Integer.parseInt(rgbaStringArray[0]);
        int green = Integer.parseInt(rgbaStringArray[1]);
        int blue = Integer.parseInt(rgbaStringArray[2]);
        assertTrue("The regularPriceColor is not grey", red == green && green == blue);

        //check line-through of regular price
        String regularPriceTextDecoration = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s.regular-price")).getCssValue("text-decoration");
        assertTrue("Regular price has incorrect font", regularPriceTextDecoration.contains("line-through"));

        //check red color of campaign price
        String campaignPriceColor = driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong.campaign-price")).getCssValue("color");
        rgbaStringArray = campaignPriceColor.replaceAll("rgb\\(|rgba\\(|\\)| ", "").split(",");
        red = Integer.parseInt(rgbaStringArray[0]);
        green = Integer.parseInt(rgbaStringArray[1]);
        blue = Integer.parseInt(rgbaStringArray[2]);
        assertTrue("The campaign PriceColor is not red", red > 0 && green == 0 && blue == 0);

        //check solid font of campaign price
        int campaignPriceTextDecoration = Integer.parseInt(driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong.campaign-price")).getCssValue("font-weight"));
        assertTrue("Campaign price has incorrect font", campaignPriceTextDecoration > 600);

        //check prices fonts
        double regularPriceFontSize = Double.parseDouble(driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper s.regular-price")).getCssValue("font-size").replace("px", ""));
        double campaignPriceFontSize = Double.parseDouble(driver.findElement(By.cssSelector("div#box-campaigns div.price-wrapper strong.campaign-price")).getCssValue("font-size").replace("px", ""));
        assertTrue("Campaign price font is not larger than regular price font", campaignPriceFontSize > regularPriceFontSize);

        //go to product page
        driver.findElement(By.xpath("//*[@id=\"box-campaigns\"]/div/ul/li/a[1]")).click();

        String nameOfProductOnProductPage = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();
        String regularPriceOnProductPage = driver.findElement(By.cssSelector("div.price-wrapper s.regular-price")).getText();
        String campaignPriceOnProductPage = driver.findElement(By.cssSelector("div.content div.price-wrapper strong.campaign-price")).getText();

        //check that values of main page and product page are the same
        assertEquals("Name of product is different", 0, nameOfProduct.compareTo(nameOfProductOnProductPage));
        assertEquals("Regular price is different", 0, regularPrice.compareTo(regularPriceOnProductPage));
        assertEquals("Campaign price is different", 0, campaignPrice.compareTo(campaignPriceOnProductPage));

        //check grey color of regular price on the product page
        String regularPriceOnProductPageColor = driver.findElement(By.cssSelector("div.price-wrapper s.regular-price")).getCssValue("color");
        rgbaStringArray = regularPriceOnProductPageColor.replaceAll("rgb\\(|rgba\\(|\\)| ", "").split(",");
        red = Integer.parseInt(rgbaStringArray[0]);
        green = Integer.parseInt(rgbaStringArray[1]);
        blue = Integer.parseInt(rgbaStringArray[2]);
        assertTrue("The regularPriceColor is not grey on the product page", red == green && green == blue);

        //check red color of campaign price on the product page
        String campaignPriceOnProductPageColor = driver.findElement(By.cssSelector("div.content div.price-wrapper strong.campaign-price")).getCssValue("color");
        rgbaStringArray = campaignPriceOnProductPageColor.replaceAll("rgb\\(|rgba\\(|\\)| ", "").split(",");
        red = Integer.parseInt(rgbaStringArray[0]);
        green = Integer.parseInt(rgbaStringArray[1]);
        blue = Integer.parseInt(rgbaStringArray[2]);
        assertTrue("The campaign PriceColor is not red on the product page", red > 0 && green == 0 && blue == 0);

        //check prices fonts on the product page
        double regularPriceOnProductPageFontSize = Double.parseDouble(driver.findElement(By.cssSelector("div.price-wrapper s.regular-price")).getCssValue("font-size").replace("px", ""));
        double campaignPriceOnProductPageFontSize = Double.parseDouble(driver.findElement(By.cssSelector("div.content div.price-wrapper strong.campaign-price")).getCssValue("font-size").replace("px", ""));
        assertTrue("Campaign price font is not larger than regular price font", campaignPriceOnProductPageFontSize > regularPriceOnProductPageFontSize);
    }

    @After
    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}