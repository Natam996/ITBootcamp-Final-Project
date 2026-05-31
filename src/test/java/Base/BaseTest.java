package Base;

import Pages.*;
import Tests.AddToCartTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.util.List;

public class BaseTest {

    public static WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public LoginPage loginPage;
    public SignupPage signupPage;
    public AccountCreatedPage accountCreatedPage;
    public ShoppingCartPage shoppingCartPage;
    public ProductsPage productsPage;
    public ProductDetailsPage productDetailsPage;
    public CheckoutPage checkoutPage;
    public PaymentPage paymentPage;
    public PaymentDonePage paymentDonePage;
    public ContactUsPage contactUsPage;
    public ReviewAddedPage reviewAddedPage;


    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void closeAdPopupIfPresent() {
        try {
            driver.switchTo().frame("aswift_1");
            WebElement closeButton = driver.findElement(By.id("dismiss-button-element"));
            closeButton.click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("No ad popup found");
        }
    }

    public void closeAdPopupIfPresent1() {
        try {
            driver.switchTo().frame("aswift_3");
            WebElement closeButton = driver.findElement(By.id("dismiss-button-element"));
            closeButton.click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("No ad popup found");
        }
    }

    public void closeAdPopupIfPresent2() {
        try {
            driver.switchTo().frame("aswift_4");
            WebElement closeButton = driver.findElement(By.id("dismiss-button-element"));
            closeButton.click();
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
            System.out.println("No ad popup found");
        }
    }
}
