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

    //--------------------------------------

    public void clickOnTextHere(){
        hereText.click();
    }
}
