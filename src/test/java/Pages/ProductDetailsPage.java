package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BaseTest {

    public ProductDetailsPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "col-sm-7")
    public WebElement productDetails;

    @FindBy(id = "quantity")
    public WebElement quantityBox;

    @FindBy(css = ".btn.btn-default.cart")
    public WebElement addToCartButton;

    @FindBy(linkText = "View Cart")
    public WebElement viewCartButton;

    @FindBy(id = "name")
    public WebElement nameField;

    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "review")
    public WebElement reviewField;

    @FindBy(id = "button-review")
    public WebElement sumbitReviewButton;

    @FindBy(css = ".alert-success.alert")
    public WebElement successAlert;
    //---------------------------------------------

    public void changeQuantity(String number){
        quantityBox.clear();
        quantityBox.sendKeys(number);
    }

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }

    public void clickOnViewCart(){
        viewCartButton.click();
    }

    public void inputNameAndEmail(String name, String email){
        nameField.clear();
        nameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void sendReview(String review){
        reviewField.clear();
        reviewField.sendKeys(review);
        sumbitReviewButton.click();
    }
}
