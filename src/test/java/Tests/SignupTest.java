package Tests;

import Base.BaseTest;
import Pages.AccountCreatedPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SignupPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SignupTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();


        homePage = new HomePage();
        loginPage = new LoginPage();
        signupPage = new SignupPage();
        accountCreatedPage = new AccountCreatedPage();
    }

    @Test
    public void UserCanSignupSuccessfully(){
        driver.navigate().to("https://automationexercise.com/"); // odlazak na sajt Automation Exercise
        Assert.assertTrue(homePage.siteLogo.isDisplayed()); // verifikacija da smo na pravom sajtu tako sto asertujemo da je logo sajta vidljiv
        homePage.clickOnSignupLoginButton();  //klik na "Signup/Login" dugme
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login"); //verifikacija da se Actual i Expected url poklapaju
        loginPage.inputName("Natalija");
        String randomEmail = "natam" + System.currentTimeMillis() + "@gmail.com";
        loginPage.inputEmail(randomEmail);
        loginPage.clickOnSignupButton();
        Assert.assertEquals(signupPage.accountInfo.getText(), "ENTER ACCOUNT INFORMATION");
        signupPage.clickOnRadioButton();
        signupPage.inputPassword("Qwerty1!");
        signupPage.clickOnDayOption("4");
        signupPage.clickOnMonthOption("December");
        signupPage.clickOnYearOption("1997");
        Assert.assertEquals(signupPage.addressInfo.getText(), "ADDRESS INFORMATION");
        signupPage.inputFirstAndLastName("Natalija","Mitic");
        signupPage.inputAddress("Street 10");
        signupPage.clickOnCountryOption("United States");
        signupPage.inputState("Florida");
        signupPage.inputCity("Miami");
        signupPage.inputZipCode("123");
        signupPage.inputMobileNumber("112234");
        signupPage.clickOnCreateAccount();
        Assert.assertTrue(accountCreatedPage.accountCreatedMessage.getText().contains("ACCOUNT CREATED"));
        accountCreatedPage.clickOnContinueButton();
        Assert.assertTrue(homePage.logoutButton.isDisplayed());
        homePage.clickOnLogoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
    }




    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
