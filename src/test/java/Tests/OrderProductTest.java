package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrderProductTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        homePage = new HomePage();
        shoppingCartPage = new ShoppingCartPage();
        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        checkoutPage = new CheckoutPage();
        paymentPage = new PaymentPage();
        paymentDonePage = new PaymentDonePage();
    }

    @Test
    public void UserCanOrderProducts() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail("natalija.8@gmail.com");
        loginPage.inputPassword("Qwerty1!");
        loginPage.clickOnLoginButton();
        Thread.sleep(3000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(homePage.loggedInName.isDisplayed());
        Assert.assertTrue(homePage.loggedInName.getText().contains("Natalija"));
        Assert.assertTrue(homePage.logoutButton.isDisplayed());
        //closeAdPopupIfPresent1();
        homePage.clickOnProducts();
        closeAdPopupIfPresent1();
        //Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products"); //asertacija da smo na Products page
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
        shoppingCartPage.clickOnProceedToCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/checkout");
        checkoutPage.clickOnPlaceOrder();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/payment");
        paymentPage.inputNameOnCard("CardName");
        paymentPage.inputCardNumber("123");
        paymentPage.inputCVC("311");
        paymentPage.inputExpiryMonth("05");
        paymentPage.inputExpiryYear("2026");
        scrollToElement(paymentPage.confirmOrderButton);
        wait.until(ExpectedConditions.elementToBeClickable(paymentPage.confirmOrderButton));
        paymentPage.clickOnConfirmOrder();
        Thread.sleep(2000);
        Assert.assertTrue(paymentDonePage.paymentDoneMessage.getText().contains("ORDER PLACED!"));
        Assert.assertTrue(paymentDonePage.paymentDoneMessage.getText().contains("Congratulations! Your order has been confirmed!"));


    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}
