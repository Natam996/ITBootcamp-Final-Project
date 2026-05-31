package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends BaseTest {

    public ProductsPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".title.text-center")
    public WebElement allProducts;

    @FindBy(className = "single-products")
    public List <WebElement> products;

    @FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/div[6]/div/div[1]/div[2]/div/a")
    public WebElement addToCartButton;

    @FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div/div[1]/div/div")
    public WebElement addedProductMessage;

    @FindBy(linkText = "View Cart")
    public WebElement viewCartButton;

    @FindBy(id = "search_product")
    public WebElement searchBarField;

    @FindBy(id = "submit_search")
    public WebElement searchButton;


    //------------------------------------------------

    /*
    public void clickOnProduct(String productName) {
        for (int i = 0; i < products.size(); i++) {
            scrollToElement(products.get(i));
            if(products.get(i).getText().contains(productName)) {
                products.get(i).click();
                break;
            }
        }
    }

     */

    public void clickOnViewCart(){
        viewCartButton.click();
    }

    public void inputSearch(String product){
        searchBarField.clear();
        searchBarField.sendKeys(product);
    }

    public void clickOnSearch(){
        searchButton.click();
    }
}
