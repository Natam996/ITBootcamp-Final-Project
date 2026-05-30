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
}
