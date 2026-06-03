package Tests;

import Base.BaseTest;
import Base.ExcelReader;
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
    public void pageSetUp() throws Exception {
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

        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail(ExcelReader.getCellValue(path,6,1));
        loginPage.inputPassword(ExcelReader.getCellValue(path,1,2));
        loginPage.clickOnLoginButton();
        Assert.assertTrue(homePage.loggedInName.getText().contains("Natalija"));
        Assert.assertTrue(homePage.logoutButton.isDisplayed());
        homePage.clickOnProducts();
        closeAdPopupIfPresent1();
        Assert.assertEquals(productsPage.allProducts.getText(), "ALL PRODUCTS");
        scrollToElement(productsPage.products.get(4));

        //Ako je potrebno da se zadrži kursor, a zatim klikne na nešto što se pojavljuje nakon što se zadrži kursor
        Actions actions = new Actions(driver);
        actions.moveToElement(productsPage.products.get(4)).perform();

        //Sada se klikne na dugme koje se pojavilo nakon prelaska mišem preko
        wait.until(ExpectedConditions.visibilityOf(productsPage.addToCartButton));
        productsPage.addToCartButton.click();
        wait.until(ExpectedConditions.visibilityOf(productsPage.addedProductMessage));
        Assert.assertTrue(productsPage.addedProductMessage.isDisplayed());
        Assert.assertTrue(productsPage.addedProductMessage.getText().contains("Your product has been added to cart"));
        productsPage.clickOnViewCart();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/view_cart");
        Assert.assertTrue(shoppingCartPage.cartDesription.getText().contains("Winter Top"));
        shoppingCartPage.clickOnProceedToCheckout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/checkout");
        scrollToElement(checkoutPage.placeOrder);
        checkoutPage.clickOnPlaceOrder();
        wait.until(ExpectedConditions.visibilityOf(paymentPage.nameOnCardField));
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/payment");

    }

    //Test metoda koja implementira test case "Korisnik može uspešno da plati i naruci komad".
    //Koristi se ExcelReader za povlacenje podataka iz Excel fajla "testData.xlsx".
    //Preduslov je da je korsinik ulogovan, zatim se sa stranice All Products doda komad u korpu, nakon cega se izbrsi placanje.
    @Test(priority = 1)
    public void UserCanOrderProducts() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        paymentPage.inputNameOnCard(ExcelReader.getCellValue(path,10,0));
        paymentPage.inputCardNumber(ExcelReader.getCellValue(path,10,1));
        paymentPage.inputCVC(ExcelReader.getCellValue(path,10,3));
        paymentPage.inputExpiryMonth(ExcelReader.getCellValue(path,10,5));
        paymentPage.inputExpiryYear(ExcelReader.getCellValue(path,10,6));
        scrollToElement(paymentPage.confirmOrderButton);
        wait.until(ExpectedConditions.elementToBeClickable(paymentPage.confirmOrderButton));
        paymentPage.clickOnConfirmOrder();
        wait.until(ExpectedConditions.visibilityOf(paymentDonePage.paymentDoneMessage));
        Assert.assertTrue(paymentDonePage.paymentDoneMessage.getText().contains("ORDER PLACED!"));
        Assert.assertTrue(paymentDonePage.paymentDoneMessage.getText().contains("Congratulations! Your order has been confirmed!"));
    }

    //Test metoda koja implementira test case "Korisnik može uspešno da plati i naruci komad sa validnim podacima iz kartice".
    //Koristi se ExcelReader za povlacenje podataka iz Excel fajla "testData.xlsx".
    //Test pada jer podaci iz kartice nisu validni: polja Card Number i cvc prihvataju slova,znakove..
    @Test(priority = 2)
    public void UserCanOrderProductWithValidCardInformation() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        paymentPage.inputNameOnCard(ExcelReader.getCellValue(path,10,0));
        paymentPage.inputCardNumber(ExcelReader.getCellValue(path,10,2));
        paymentPage.inputCVC(ExcelReader.getCellValue(path,10,4));
        paymentPage.inputExpiryMonth(ExcelReader.getCellValue(path,10,5));
        paymentPage.inputExpiryYear(ExcelReader.getCellValue(path,10,6));
        scrollToElement(paymentPage.confirmOrderButton);
        wait.until(ExpectedConditions.elementToBeClickable(paymentPage.confirmOrderButton));
        paymentPage.clickOnConfirmOrder();
        wait.until(ExpectedConditions.visibilityOf(paymentDonePage.paymentDoneMessage));
        Assert.assertFalse(paymentDonePage.paymentDoneMessage.getText().contains("ORDER PLACED!"));
    }


    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}
