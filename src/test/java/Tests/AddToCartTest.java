package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import Pages.ShoppingCartPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
        productDetailsPage = new ProductDetailsPage();

    }

    @Test(priority = 1)
    public void UserCanAddToCartOneProduct() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        homePage.clickOnCartIcon();
        Assert.assertTrue(shoppingCartPage.shoppingCartMessage.getText().contains("Shopping Cart")); //provera da smo na pravom sajtu
        Assert.assertTrue(shoppingCartPage.shoppingCartMessage.getText().contains("Cart is empty! Click here to buy products")); //provera da je korpa prazna
        shoppingCartPage.clickOnTextHere();
        //Thread.sleep(5000);
        closeAdPopupIfPresent();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products"); //asertacija da smo na Products page
        Assert.assertEquals(productsPage.allProducts.getText(), "ALL PRODUCTS");
        scrollToElement(productsPage.products.get(4));
        //If you need to hover and then click something that appears after hover:
        Actions actions = new Actions(driver);
        actions.moveToElement(productsPage.products.get(4)).perform();

        // Now click the button that appeared after hover
        wait.until(ExpectedConditions.visibilityOf(productsPage.addToCartButton));
        productsPage.addToCartButton.click();
        Thread.sleep(3000);
        Assert.assertTrue(productsPage.addedProductMessage.isDisplayed());
        Assert.assertTrue(productsPage.addedProductMessage.getText().contains("Your product has been added to cart"));
        productsPage.clickOnViewCart();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
        Assert.assertTrue(shoppingCartPage.cartDesription.getText().contains("Winter Top"));
    }



    @Test(priority = 2)
    public void UserCanChangeQuantityOfProduct() throws InterruptedException {
        UserCanAddToCartOneProduct();
        shoppingCartPage.clickOnProduct();
        Assert.assertTrue(driver.getCurrentUrl().contains("product_details"));
        Assert.assertTrue(productDetailsPage.productDetails.getText().contains("Winter Top"));
        Assert.assertTrue(productDetailsPage.productDetails.getText().contains("Quantity"));
        Assert.assertTrue(productDetailsPage.productDetails.getText().contains("Availability"));
        wait.until(ExpectedConditions.visibilityOf(productDetailsPage.quantityBox));
        Assert.assertTrue(productDetailsPage.quantityBox.getAttribute("value").contains("1"));
        productDetailsPage.changeQuantity("3");
        Assert.assertTrue(productDetailsPage.quantityBox.getAttribute("value").contains("3"));
        productDetailsPage.clickOnAddToCartButton();
        productsPage.clickOnViewCart();
        shoppingCartPage.cartQuantity.getText().contains("4");
        Assert.assertTrue(shoppingCartPage.cartDesription.getText().contains("Winter Top"));
    }



    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

