package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest {


    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".logo.pull-left")
    public WebElement siteLogo;

    @FindBy(linkText = "Signup / Login")
    public WebElement SignUpLoginButton;

    @FindBy(linkText = "Logout")
    public WebElement logoutButton;

    @FindBy(xpath = "/html/body/header/div/div/div/div[2]/div/ul/li[10]/a")
    public WebElement loggedInName;



    //-------------------------------------

    //metoda za klik na "Signup/Login" dugme
    public void clickOnSignupLoginButton(){
        SignUpLoginButton.click();
    }

    public void clickOnLogoutButton(){
        logoutButton.click();
    }

}
