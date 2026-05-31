package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Brands extends BaseTest {

    public Brands(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".brands-name li a")
    public List<WebElement> brandsNames;


    //-------------------------------

    public void clickOnBrandsName(String brand){
        for (int i = 0; i < brandsNames.size(); i++) {
            //scrollToElement(products.get(i));
            if(brandsNames.get(i).getText().contains(brand)) {
                brandsNames.get(i).click();
                closeAdPopupIfPresent1();
                break;
            }
        }
    }


}
