package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignupPage extends BaseTest {

    public SignupPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/h2/b")
    public WebElement accountInfo;

    @FindBy(id = "id_gender2")
    public WebElement radioButton;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "uniform-days")
    public List <WebElement> dayOptions;

    @FindBy(id = "months")
    public List <WebElement> monthOptions;

    @FindBy(id = "years")
    public List <WebElement> yearOptions;

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/form/h2/b")
    public WebElement addressInfo;

    @FindBy(id = "first_name")
    public WebElement firstNameField;

    @FindBy(id = "last_name")
    public WebElement lastNameField;

    @FindBy(id = "address1")
    public WebElement addressField;

    @FindBy(id = "country")
    public List<WebElement> countryOptions;

    @FindBy(id = "state")
    public WebElement stateField;

    @FindBy(id = "city")
    public WebElement cityField;

    @FindBy(id = "zipcode")
    public WebElement zipCodeField;

    @FindBy(id = "mobile_number")
    public  WebElement mobileField;

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/form/button")
    public WebElement createAccountButton;

    //---------------------------------------------


    public void clickOnRadioButton(){
        radioButton.click();
    }

    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnDayOption(String number) {
        for (int i = 0; i < dayOptions.size(); i++) {
            scrollToElement(dayOptions.get(i));
            if (dayOptions.get(i).getText().equals(number)) {
                dayOptions.get(i).click();
                break;
            }
        }
    }

    public void clickOnMonthOption(String month) {
        for (int i = 0; i < monthOptions.size(); i++) {
            scrollToElement(monthOptions.get(i));
            if (monthOptions.get(i).getText().equals(month)) {
                monthOptions.get(i).click();
                break;
            }
        }
    }

    public void clickOnYearOption(String yearNumber) {
        for (int i = 0; i < yearOptions.size(); i++) {
            scrollToElement(yearOptions.get(i));
            if (yearOptions.get(i).getText().equals(yearNumber)) {
                yearOptions.get(i).click();
                break;
            }
        }
    }

    public void inputFirstAndLastName(String firstName, String lastName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void inputAddress(String address){
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void clickOnCountryOption(String country){
        for (int i = 0; i < countryOptions.size(); i++) {
            scrollToElement(countryOptions.get(i));
            if (countryOptions.get(i).getText().equals(country)) {
                countryOptions.get(i).click();
                break;
            }
        }
    }

    public void inputState(String state){
        stateField.clear();
        stateField.sendKeys(state);
    }

    public void inputCity(String city){
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void inputZipCode(String number){
        zipCodeField.clear();
        zipCodeField.sendKeys(number);
    }

    public void inputMobileNumber(String mobile){
        mobileField.clear();
        mobileField.sendKeys(mobile);
    }

    public void clickOnCreateAccount(){
        createAccountButton.click();
    }
}
