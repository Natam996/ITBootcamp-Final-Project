package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ShoppingCartPage;
import org.openqa.selenium.chrome.ChromeDriver;
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
    }

    @Test
    public void UserCanOrderProducts(){
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail("natalija.8@gmail.com");
        loginPage.inputPassword("Qwerty1!");
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(homePage.loggedInName.isDisplayed());
        Assert.assertTrue(homePage.loggedInName.getText().contains("Natalija"));
        Assert.assertTrue(homePage.logoutButton.isDisplayed());

        
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}
