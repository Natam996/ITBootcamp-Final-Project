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

    @FindBy(css = "input[data-qa='signup-email']")
    public WebElement emailField;

    @FindBy(xpath = "/html/body/section/div/div/div[3]/div/form/button")
    public WebElement signupButton;

    @FindBy(className = "signup-form")
    public WebElement signupForm;


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
}
