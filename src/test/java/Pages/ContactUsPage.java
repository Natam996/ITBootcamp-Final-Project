package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.print.attribute.standard.MediaSize;

public class ContactUsPage extends BaseTest {

    public ContactUsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    public WebElement nameField;

    @FindBy(name = "email")
    public WebElement emailField;

    @FindBy(name = "subject")
    public WebElement subjectField;

    @FindBy(name = "message")
    public WebElement messageField;

    @FindBy(name = "upload_file")
    public WebElement browseButton;

    @FindBy(name = "submit")
    public WebElement submitButton;


    //------------------------------------------

    public void inputNameEmailSubject(String name, String email, String subject){
        nameField.clear();
        nameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
        subjectField.clear();
        subjectField.sendKeys(subject);
    }

    public void inputMessage(String message){
        messageField.clear();
        messageField.sendKeys(message);
    }

    public void clickOnSubmit(){
        submitButton.click();
    }

}
