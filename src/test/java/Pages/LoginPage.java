package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    public WebElement nameField;

    @FindBy(xpath = "/html/body/section/div/div/div[3]/div/form/input[3]")
    public WebElement emailField;

    @FindBy(xpath = "/html/body/section/div/div/div[3]/div/form/button")
    public WebElement signupButton;

    @FindBy(xpath = "/html/body/section/div/div/div[1]/div/form/input[2]")
    public WebElement emailLoginField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(xpath = "/html/body/section/div/div/div[1]/div/form/button")
    public WebElement loginButton;

    @FindBy(className = "login-form")
    public WebElement loginForm;

    @FindBy(id = "form")
    public WebElement emailErrorMessage;

    @FindBy(xpath = "/html/body/section/div/div/div[1]/div/form/p")
    public WebElement passwordErrorMessage;

    //------------------------------------------

    public void inputName(String name){
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void inputEmail(String email){
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickOnSignupButton(){
        signupButton.click();
    }

    public void inputLoginEmail(String email){
        emailLoginField.clear();
        emailLoginField.sendKeys(email);
    }

    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

}
