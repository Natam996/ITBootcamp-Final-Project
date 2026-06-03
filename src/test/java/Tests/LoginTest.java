package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.LoginPage;
import org.openqa.selenium.By;
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
    public void UserCanLoginWithValidCredentials() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail(ExcelReader.getCellValue(path, 6,1));
        loginPage.inputPassword(ExcelReader.getCellValue(path,1,2));
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        Assert.assertTrue(homePage.loggedInName.isDisplayed());
        Assert.assertTrue(homePage.loggedInName.getText().contains("Natalija"));
        Assert.assertTrue(homePage.logoutButton.isDisplayed());
    }

    @Test(priority = 2)
    public void UserCannotLoginWithInvalidEmail() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail(ExcelReader.getCellValue(path, 4,1));
        loginPage.inputPassword(ExcelReader.getCellValue(path, 1,2));
        loginPage.clickOnLoginButton();
        String errorMessage = driver.findElement(By.cssSelector("input[data-qa='login-email']"))
                .getAttribute("validationMessage");
        System.out.println(errorMessage);
        Assert.assertTrue(errorMessage.contains("Please include an '@' in the email address."));
    }

    @Test(priority = 3)
    public void UserCannotLoginWithInvalidPassword() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        driver.navigate().to("https://automationexercise.com/login");
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
        loginPage.inputLoginEmail(ExcelReader.getCellValue(path, 1,1));
        loginPage.inputPassword(ExcelReader.getCellValue(path,3,2));
        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.passwordErrorMessage.getText().contains("Your email or password is incorrect!"));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
