package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDeletedPage extends BaseTest {

    public AccountDeletedPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".col-sm-9.col-sm-offset-1")
    public WebElement accountDeletedMessage;

    @FindBy(css = ".btn.btn-primary")
    public WebElement continueButton;

    //----------------------------------------

    public void clickOnContinueButton(){
        continueButton.click();
    }
}
