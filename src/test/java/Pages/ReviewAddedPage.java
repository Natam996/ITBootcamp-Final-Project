package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReviewAddedPage extends BaseTest {

    public ReviewAddedPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".status.alert.alert-success")
    public WebElement reviewAddedMessage;
}
