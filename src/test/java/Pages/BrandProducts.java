package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrandProducts extends BaseTest {

    public BrandProducts(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".title.text-center")
    public WebElement brandProductTitle;
}
