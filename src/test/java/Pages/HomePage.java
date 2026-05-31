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

    @FindBy(linkText = "Cart")
    public WebElement cartIcon;

    @FindBy(xpath = "/html/body/header/div/div/div/div[2]/div/ul/li[10]/a")
    public WebElement loggedInName;

    @FindBy(xpath = "/html/body/header/div/div/div/div[2]/div/ul/li[2]/a")
    public WebElement productsButton;

    @FindBy(linkText = "Contact us")
    public WebElement contactUsButton;



    //-------------------------------------

    //metoda za klik na "Signup/Login" dugme
    public void clickOnSignupLoginButton(){
        SignUpLoginButton.click();
    }

    public void clickOnLogoutButton(){
        logoutButton.click();
    }

    public void clickOnCartIcon(){
        cartIcon.click();
    }

    public void clickOnProducts(){
        productsButton.click();
    }

    public void clickOnContactUs(){
        contactUsButton.click();
    }
}
