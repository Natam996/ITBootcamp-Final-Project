package Tests;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @Test(priority = 1)
    public void UserCanLoginWithValidCredentials(){
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

    @Test(priority = 2)
    public void UserCannotLoginWithInvalidEmail() {
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail("nata");
        loginPage.inputPassword("Qwerty1!");
        loginPage.clickOnLoginButton();
        System.out.println(loginPage.emailErrorMessage.getText());
        Assert.assertTrue(loginPage.emailErrorMessage.getText().contains("Login to your account"));
    }

    @Test(priority = 3)
    public void UserCannotLoginWithInvalidPassword() {
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail("natalija.8@gmail.com");
        loginPage.inputPassword("QWA");
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.passwordErrorMessage.getText().contains("Your email or password is incorrect!"));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
