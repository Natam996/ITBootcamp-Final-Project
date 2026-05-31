package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SidebarCategory extends BaseTest {


    public SidebarCategory(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".panel-title a")
    public List<WebElement> categoryPeople;

    @FindBy(css = "#Women .panel-body a")
    public List<WebElement> womenSubCategories;

    //---------------------------


    public void clickOnCategory(String person) throws InterruptedException {
        for (int i = 0; i < categoryPeople.size(); i++) {
            //scrollToElement(products.get(i));
            if(categoryPeople.get(i).getText().contains(person)) {
                categoryPeople.get(i).click();
                closeAdPopupIfPresent1();
                break;
            }
        }
    }

}
