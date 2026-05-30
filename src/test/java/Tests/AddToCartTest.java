package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductsPage;
import Pages.ShoppingCartPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        homePage = new HomePage();
        shoppingCartPage= new ShoppingCartPage();
        productsPage = new ProductsPage();

    }

    @Test
    public void UserCanAddToCartOneProduct() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        homePage.clickOnCartIcon();
        Assert.assertTrue(shoppingCartPage.shoppingCartMessage.getText().contains("Shopping Cart")); //provera da smo na pravom sajtu
        Assert.assertTrue(shoppingCartPage.shoppingCartMessage.getText().contains("Cart is empty! Click here to buy products")); //provera da je korpa prazna
        shoppingCartPage.clickOnTextHere();
        closeAdPopupIfPresent();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products"); //asertacija da smo na Products page
        Assert.assertEquals(productsPage.allProducts.getText(), "ALL PRODUCTS");






    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

