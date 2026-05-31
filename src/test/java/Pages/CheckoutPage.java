package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {

    public CheckoutPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Place Order")
    public WebElement placeOrder;


    //--------------------------------------------

    public void clickOnPlaceOrder(){
        placeOrder.click();
    }

}
