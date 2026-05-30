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

    @FindBy(className = "cart_quantity_delete")
    public WebElement xButton;

    //--------------------------------------

    public void clickOnTextHere(){
        hereText.click();
    }

    public void clickOnX(){
        xButton.click();
    }
}
