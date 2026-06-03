package Tests;

import Base.BaseTest;
import Pages.ContactUsPage;
import Pages.HomePage;
import Pages.ReviewAddedPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContactSiteTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        homePage = new HomePage();
        contactUsPage = new ContactUsPage();
        reviewAddedPage = new ReviewAddedPage();
    }

    //Test metoda koja implementira test case "Korisnik može uspešno da kontaktira sajt bez dodavanja fajla".
    @Test(priority = 1)
    public void UserCanContactSiteWithoutUploadingFile() {
        driver.navigate().to("https://automationexercise.com/");
        homePage.clickOnContactUs();
        contactUsPage.inputNameEmailSubject("Natalija", "natalija.8@gmail.com", "wrong top");
        contactUsPage.inputMessage("The top i got was wrong!");
        contactUsPage.clickOnSubmit();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Assert.assertTrue(reviewAddedPage.reviewAddedMessage.getText().contains("Success! Your details have been submitted successfully."));
    }

    //Test metoda koja implementira test case "Korisnik može uspešno da kontaktira sajt sa dodavanjem fajla".
    @Test(priority = 2)
    public void UserCanContactSiteAndUploadFile() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        homePage.clickOnContactUs();
        contactUsPage.inputNameEmailSubject("Natalija", "natalija.8@gmail.com", "wrong top");
        contactUsPage.inputMessage("The top i got was wrong!");
        Thread.sleep(3000);
        contactUsPage.fileUploadInput.sendKeys("C:\\Users\\Natalija Mitic\\Desktop\\1\\slika.jpg");
        contactUsPage.clickOnSubmit();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        Assert.assertTrue(reviewAddedPage.reviewAddedMessage.getText().contains("Success! Your details have been submitted successfully."));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
