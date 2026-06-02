package Tests;

import Base.BaseTest;
import Base.ExcelReader;
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
    public void UserCanSignupSuccessfully() throws Exception {

        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";

        loginPage.inputName("Natalija");
        String randomEmail = "natam" + System.currentTimeMillis() + "@gmail.com";
        loginPage.inputEmail(randomEmail);
        loginPage.clickOnSignupButton();
        Assert.assertEquals(signupPage.accountInfo.getText(), "ENTER ACCOUNT INFORMATION");
        signupPage.clickOnRadioButton();
        signupPage.inputPassword(ExcelReader.getCellValue(path, 1, 0));
        signupPage.clickOnDayOption(ExcelReader.getCellValue(path, 1, 1));
        signupPage.clickOnMonthOption(ExcelReader.getCellValue(path, 1, 2));
        signupPage.clickOnYearOption(ExcelReader.getCellValue(path, 1, 3));
        signupPage.inputFirstAndLastName(ExcelReader.getCellValue(path, 1, 4), ExcelReader.getCellValue(path, 1, 5));
        Assert.assertTrue(signupPage.firstNameField.getAttribute("value").matches("[a-zA-Z]+"));
        Assert.assertTrue(signupPage.lastNameField.getAttribute("value").matches("[a-zA-Z]+"));
        signupPage.inputAddress(ExcelReader.getCellValue(path, 1, 6));
        signupPage.clickOnCountryOption(ExcelReader.getCellValue(path, 1, 7));
        signupPage.inputState(ExcelReader.getCellValue(path, 1, 8));
        signupPage.inputCity(ExcelReader.getCellValue(path, 1, 9));
        signupPage.inputZipCode(ExcelReader.getCellValue(path, 1, 10));
        signupPage.inputMobileNumber(ExcelReader.getCellValue(path, 1, 11));
        Assert.assertTrue(signupPage.mobileField.getAttribute("value").matches("[0-9]+"));
        /*
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
         */
        Thread.sleep(3000);
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

//verifikacija da test ne prolazi sa nevalidnim imenom, prezimenom i telefonom.
// Polja za ime i prezime dozvoljavaju brojeve, karaktere..Polje za telefon dozvoljava slova itd...
    @Test(priority = 5)
    public void invalidNameLastNameAndMobileForm() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";

        loginPage.inputName("Natalija");
        String randomEmail = "natam" + System.currentTimeMillis() + "@gmail.com";
        loginPage.inputEmail(randomEmail);
        loginPage.clickOnSignupButton();
        Assert.assertEquals(signupPage.accountInfo.getText(), "ENTER ACCOUNT INFORMATION");
        signupPage.clickOnRadioButton();
        signupPage.inputPassword(ExcelReader.getCellValue(path, 1, 0));
        signupPage.clickOnDayOption(ExcelReader.getCellValue(path, 1, 1));
        signupPage.clickOnMonthOption(ExcelReader.getCellValue(path, 1, 2));
        signupPage.clickOnYearOption(ExcelReader.getCellValue(path, 1, 3));
        signupPage.inputFirstAndLastName(ExcelReader.getCellValue(path, 3, 4), ExcelReader.getCellValue(path, 3, 5));
        Assert.assertTrue(signupPage.firstNameField.getAttribute("value").matches("[a-zA-Z]+"));
        Assert.assertTrue(signupPage.lastNameField.getAttribute("value").matches("[a-zA-Z]+"));
        signupPage.inputAddress(ExcelReader.getCellValue(path, 1, 6));
        signupPage.clickOnCountryOption(ExcelReader.getCellValue(path, 1, 7));
        signupPage.inputState(ExcelReader.getCellValue(path, 1, 8));
        signupPage.inputCity(ExcelReader.getCellValue(path, 1, 9));
        signupPage.inputZipCode(ExcelReader.getCellValue(path, 3, 10));
        signupPage.inputMobileNumber(ExcelReader.getCellValue(path, 3, 11));
        Assert.assertTrue(signupPage.mobileField.getAttribute("value").matches("[0-9]+"));
        signupPage.clickOnCreateAccount();
        Assert.assertTrue(accountCreatedPage.accountCreatedMessage.getText().contains("ACCOUNT CREATED"));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
