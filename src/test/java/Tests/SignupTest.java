package Tests;

import Base.BaseTest;
import Pages.AccountCreatedPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        driver.navigate().to("https://automationexercise.com/"); // odlazak na sajt Automation Exercise
        Assert.assertTrue(homePage.siteLogo.isDisplayed()); // verifikacija da smo na pravom sajtu tako sto asertujemo da je logo sajta vidljiv
        homePage.clickOnSignupLoginButton();  //klik na "Signup/Login" dugme
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login"); //verifikacija da se Actual i Expected url poklapaju

    }

    @Test(priority = 1)
    public void UserCanSignupSuccessfully(){

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
        signupPage.inputFirstAndLastName("Natalija","Vitic");
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

    @Test(priority = 2)
    public void UserCannotSignupWithExistingEmail(){
        loginPage.inputName("Natalija");
        loginPage.inputEmail("natalija.8@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.signupButton));
        loginPage.clickOnSignupButton();
        Assert.assertTrue(loginPage.signupForm.getText().contains("Email Address already exist!"));
    }

    @Test(priority = 3)
    public void UserCannotSignupWithoutEmail() throws InterruptedException {
        loginPage.inputName("Natalija");
        loginPage.clickOnSignupButton();
        Thread.sleep(3000);
        String validationMessage = driver.findElement(By.cssSelector("input[placeholder='Email Address']"))
                .getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Please fill out this field.");
    }

    @Test(priority = 4)
    public void UserCannotSignupWithInvalidEmailForm() throws InterruptedException {
        loginPage.inputName("Natalija");
        loginPage.inputEmail("email");
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.signupButton));
        loginPage.clickOnSignupButton();
        String validationMessage = driver.findElement(By.cssSelector("input[data-qa='signup-email']"))
                .getAttribute("validationMessage");
        System.out.println(validationMessage);
        Assert.assertTrue(validationMessage.contains("Please include an '@' in the email address."));
    }





    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
