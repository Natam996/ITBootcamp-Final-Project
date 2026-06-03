package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.*;
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
        accountDeletedPage = new AccountDeletedPage();

        //Prvi deo koda koji je za sve test metode isti:
        // odlazak na Homepage sajta, asertacija da je korisnik stvarno na Homepage strani, klik na Signup/Login dugme i odlazak na Login stranicu.
        driver.navigate().to("https://automationexercise.com/"); // odlazak na sajt Automation Exercise
        Assert.assertTrue(homePage.siteLogo.isDisplayed()); // verifikacija da smo na pravom sajtu tako sto asertujemo da je logo sajta vidljiv
        homePage.clickOnSignupLoginButton();  //klik na "Signup/Login" dugme
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login"); //verifikacija da se Actual i Expected url poklapaju

    }

    //Test metoda koja implementira test case "Korisnik može uspešno da se registruje"
    //Koriscen je ExcelReader za povlacenje podataka iz Excel fajla "testData.xlsx"
    @Test(priority = 1)
    public void UserCanSignupSuccessfully() throws Exception {

        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";

        loginPage.inputName(ExcelReader.getCellValue(path,1,0));
        //String randomEmail = "natam" + System.currentTimeMillis() + "@gmail.com";
        //loginPage.inputEmail(randomEmail);
        loginPage.inputEmail(ExcelReader.getCellValue(path,1,1));
        loginPage.clickOnSignupButton();
        Assert.assertEquals(signupPage.accountInfo.getText(), "ENTER ACCOUNT INFORMATION");
        signupPage.clickOnRadioButton();
        signupPage.inputPassword(ExcelReader.getCellValue(path, 1, 2));
        signupPage.clickOnDayOption(ExcelReader.getCellValue(path, 1, 3));
        signupPage.clickOnMonthOption(ExcelReader.getCellValue(path, 1, 4));
        signupPage.clickOnYearOption(ExcelReader.getCellValue(path, 1, 5));
        signupPage.inputFirstAndLastName(ExcelReader.getCellValue(path, 1, 6), ExcelReader.getCellValue(path, 1, 7));
        Assert.assertTrue(signupPage.firstNameField.getAttribute("value").matches("[a-zA-Z]+"));
        Assert.assertTrue(signupPage.lastNameField.getAttribute("value").matches("[a-zA-Z]+"));
        signupPage.inputAddress(ExcelReader.getCellValue(path, 1, 8));
        signupPage.clickOnCountryOption(ExcelReader.getCellValue(path, 1, 9));
        signupPage.inputState(ExcelReader.getCellValue(path, 1, 10));
        signupPage.inputCity(ExcelReader.getCellValue(path, 1, 11));
        signupPage.inputZipCode(ExcelReader.getCellValue(path, 1, 12));
        signupPage.inputMobileNumber(ExcelReader.getCellValue(path, 1, 13));
        Assert.assertTrue(signupPage.mobileField.getAttribute("value").matches("[0-9]+"));
        Thread.sleep(3000);
        signupPage.clickOnCreateAccount();
        Assert.assertTrue(accountCreatedPage.accountCreatedMessage.getText().contains("ACCOUNT CREATED"));
        driver.navigate().refresh();
        accountCreatedPage.clickOnContinueButton();
        Assert.assertTrue(homePage.logoutButton.isDisplayed());
        //homePage.clickOnDeleteAccount();
        //Assert.assertTrue(accountDeletedPage.accountDeletedMessage.getText().contains("ACCOUNT DELETED"));
        //homePage.clickOnLogoutButton();
        //Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/login");
    }

    //Test metoda koja implementira test case "Korisnik ne može da se registruje sa vec postojecom email adresom"
    @Test(priority = 2)
    public void UserCannotSignupWithExistingEmail() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";

        loginPage.inputName(ExcelReader.getCellValue(path, 1,0));
        loginPage.inputEmail(ExcelReader.getCellValue(path, 1,1));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.signupButton));
        loginPage.clickOnSignupButton();
        Assert.assertTrue(loginPage.signupForm.getText().contains("Email Address already exist!"));
    }

    //Test metoda koja implementira test case "Korisnik ne može da se registruje bez unete email adrese"
    @Test(priority = 3)
    public void UserCannotSignupWithoutEmail() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";

        loginPage.inputName(ExcelReader.getCellValue(path, 1,0));
        loginPage.clickOnSignupButton();
        Thread.sleep(3000);
        String validationMessage = driver.findElement(By.cssSelector("input[placeholder='Email Address']"))
                .getAttribute("validationMessage");
        Assert.assertEquals(validationMessage, "Please fill out this field.");
    }

    //Test metoda koja implementira test case "Korisnik ne može da se registruje sa nevalidnom email formom"
    @Test(priority = 4)
    public void UserCannotSignupWithInvalidEmailForm() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        loginPage.inputName(ExcelReader.getCellValue(path, 1,0));
        loginPage.inputEmail(ExcelReader.getCellValue(path, 4,1));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.signupButton));
        loginPage.clickOnSignupButton();
        String validationMessage = driver.findElement(By.cssSelector("input[data-qa='signup-email']"))
                .getAttribute("validationMessage");
        System.out.println(validationMessage);
        Assert.assertTrue(validationMessage.contains("Please include an '@' in the email address."));
    }

    //Test metoda koja implementira test case "Korisnik ne može da se registruje sa nevalidnom formom za ime, prezime i mobilni telefon"
    //Test ne prolazi zato sto polja za ime i prezime dozvoljavaju brojeve, karaktere..Polje za telefon dozvoljava slova itd...
    @Test(priority = 5)
    public void UserCannotSignupWithInvalidNameLastNameAndMobileForm() throws Exception {
        String path = "C:\\Users\\Natalija Mitic\\Desktop\\1\\testData.xlsx";
        loginPage.inputName(ExcelReader.getCellValue(path,1,0));
        loginPage.inputEmail(ExcelReader.getCellValue(path,2,1));
        loginPage.clickOnSignupButton();
        Assert.assertEquals(signupPage.accountInfo.getText(), "ENTER ACCOUNT INFORMATION");
        signupPage.clickOnRadioButton();
        signupPage.inputPassword(ExcelReader.getCellValue(path, 3, 2));
        signupPage.clickOnDayOption(ExcelReader.getCellValue(path, 3, 3));
        signupPage.clickOnMonthOption(ExcelReader.getCellValue(path, 3, 4));
        signupPage.clickOnYearOption(ExcelReader.getCellValue(path, 3, 5));
        signupPage.inputFirstAndLastName(ExcelReader.getCellValue(path, 3, 6), ExcelReader.getCellValue(path, 3, 7));
        Assert.assertTrue(signupPage.firstNameField.getAttribute("value").matches("[a-zA-Z]+"));
        Assert.assertTrue(signupPage.lastNameField.getAttribute("value").matches("[a-zA-Z]+"));
        signupPage.inputAddress(ExcelReader.getCellValue(path, 3, 8));
        signupPage.clickOnCountryOption(ExcelReader.getCellValue(path, 3, 9));
        signupPage.inputState(ExcelReader.getCellValue(path, 3, 10));
        signupPage.inputCity(ExcelReader.getCellValue(path, 3, 11));
        signupPage.inputZipCode(ExcelReader.getCellValue(path, 3, 12));
        signupPage.inputMobileNumber(ExcelReader.getCellValue(path, 3, 13));
        Assert.assertTrue(signupPage.mobileField.getAttribute("value").matches("[0-9]+"));
        signupPage.clickOnCreateAccount();
        Assert.assertTrue(accountCreatedPage.accountCreatedMessage.getText().contains("ACCOUNT CREATED"));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
