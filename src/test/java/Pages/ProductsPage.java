package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BaseTest {

    public ProductsPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".title.text-center")
    public WebElement allProducts;

}
