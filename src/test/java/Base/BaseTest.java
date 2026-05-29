package Base;

import Pages.AccountCreatedPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public static WebDriver driver;
    public WebDriverWait wait;
    public HomePage homePage;
    public LoginPage loginPage;
    public SignupPage signupPage;
    public AccountCreatedPage accountCreatedPage;




    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
