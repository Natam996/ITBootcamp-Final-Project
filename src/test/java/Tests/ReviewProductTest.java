package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ReviewProductTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        homePage = new HomePage();
        productsPage = new ProductsPage();
        productDetailsPage = new ProductDetailsPage();
    }

    @Test
    public void UserCanReviewProduct() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        homePage.clickOnProducts();
        closeAdPopupIfPresent1();
        closeAdPopupIfPresent2();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/products"); //asertacija da smo na Products page
        Assert.assertEquals(productsPage.allProducts.getText(), "ALL PRODUCTS");
        scrollToElement(productsPage.products.get(4));
        productsPage.clickOnViewProduct();
        productDetailsPage.inputNameAndEmail("Natalija", "natalija.8@gmail.com");
        productDetailsPage.sendReview("Top is not the right size!");
        Assert.assertTrue(productDetailsPage.successAlert.getText().contains("Thank you for your review"));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
