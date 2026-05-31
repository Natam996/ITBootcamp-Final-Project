package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.print.attribute.standard.MediaSize;

public class PaymentPage extends BaseTest {

    public PaymentPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name_on_card")
    public WebElement nameOnCardField;

    @FindBy(name = "card_number")
    public WebElement cardNumberField;


    @FindBy(name = "cvc")
    public WebElement cvcField;

    @FindBy(name = "expiry_month")
    public WebElement expiryMonthField;

    @FindBy(name = "expiry_year")
    public WebElement expiryYearField;

    @FindBy(id = "submit")
    public WebElement confirmOrderButton;
    //--------------------------------

    public void inputNameOnCard(String cardName){
        nameOnCardField.clear();
        nameOnCardField.sendKeys(cardName);
    }

    public void inputCardNumber(String number){
        cardNumberField.clear();
        cardNumberField.sendKeys(number);
    }

    public void inputCVC(String cvc){
        cvcField.clear();
        cvcField.sendKeys(cvc);
    }

    public void inputExpiryMonth(String month){
        expiryMonthField.clear();
        expiryMonthField.sendKeys(month);
    }

    public void inputExpiryYear(String year){
        expiryYearField.clear();
        expiryYearField.sendKeys(year);
    }

    public void clickOnConfirmOrder(){
        confirmOrderButton.click();
    }
}
