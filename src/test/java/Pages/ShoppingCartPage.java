package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BaseTest {

    public ShoppingCartPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "cart_items")
    public WebElement shoppingCartMessage;

    @FindBy(linkText = "here")
    public WebElement hereText;

    @FindBy(className = "cart_description")
    public WebElement cartDesription;

    @FindBy(linkText = "Winter Top")
    public WebElement productWinterTop;

    @FindBy(className = "cart_quantity")
    public WebElement cartQuantity;

    @FindBy(css = ".btn.btn-default.check_out")
    public WebElement proceedToCheckout;

    //--------------------------------------

    public void clickOnTextHere(){
        hereText.click();
    }

    public void clickOnProduct(){
        productWinterTop.click();
    }

    public void clickOnProceedToCheckout(){
        proceedToCheckout.click();
    }
}
