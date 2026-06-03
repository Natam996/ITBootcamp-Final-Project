package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchProductsTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        homePage = new HomePage();
        productsPage = new ProductsPage();
        sidebarCategory = new SidebarCategory();
        brands = new Brands();
        brandProducts = new BrandProducts();
    }

    //Test metoda koja implementira test case "Korisnik može da pretrazuje komad preko stranice All Products"
    @Test(priority = 1)
    public void UserCanSearchProductFromAllProducts() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        homePage.clickOnProducts();
        Thread.sleep(3000);
        closeAdPopupIfPresent1();
        Assert.assertEquals(productsPage.allProducts.getText(), "ALL PRODUCTS");
        scrollToElement(productsPage.products.get(4));
        Assert.assertTrue(productsPage.products.get(4).getText().contains("Winter Top"));
    }

    //Test metoda koja implementira test case "Korisnik može da pretrazuje komad preko sidebar Category", i to klikom na kategoriju a zatim na sab-kategoriju.
    @Test(priority = 2)
    public void UserCanSearchProductFromCategorySidebar() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        scrollToElement(sidebarCategory.categoryPeople.get(0));
        closeAdPopupIfPresent2();
        sidebarCategory.clickOnCategory("WOMEN");
        closeAdPopupIfPresent1();
        closeAdPopupIfPresent2();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(sidebarCategory.womenSubCategories.get(0)));
        sidebarCategory.womenSubCategories.get(1).click();
        Assert.assertTrue(productsPage.products.stream().anyMatch(p -> p.getText().contains("Winter Top")));
    }

    //Test metoda koja implementira test case "Korisnik može da pretrazuje komad preko Brands opcije"
    @Test(priority = 3)
    public void UserCanSearchProductFromBrands() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        scrollToElement(brands.brandsNames.get(0));
        brands.clickOnBrandsName("MAST & HARBOUR");
        driver.navigate().refresh();
        //closeAdPopupIfPresent1();
        //closeAdPopupIfPresent2();
        Assert.assertTrue(brandProducts.brandProductTitle.getText().contains("BRAND - MAST & HARBOUR PRODUCTS"));
        Assert.assertTrue(productsPage.products.stream().anyMatch(p -> p.getText().contains("Winter Top")));
    }

    //Test metoda koja implementira test case "Korisnik može da pretrazuje komad preko searchbar-a", gde se rucno unosi naziv zeljenog komada.
    @Test(priority = 4)
    public void UserCanSearchProductFromSearchBar() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        homePage.clickOnProducts();
        Thread.sleep(3000);
        closeAdPopupIfPresent1();
        Assert.assertEquals(productsPage.allProducts.getText(), "ALL PRODUCTS");
        productsPage.inputSearch("Winter Top");
        productsPage.clickOnSearch();
        Assert.assertTrue(productsPage.products.stream().anyMatch(p -> p.getText().contains("Winter Top")));
    }

    //Test metoda koja implementira test case "Korisnik može da pretrazuje komad preko searchbar-a i to bez razmaka", gde se rucno unosi naziv zeljenog komada i to bez razmaka.
    //Asertacija da se nadje proizvod kada se ukuca u searchbar, bez razmaka i sam malim slovima.
    // Test pada, ne pronalazi trazeni komad.
    @Test(priority = 5)
    public void UserCanSearchProductFromSearchBarNoSpaces() throws InterruptedException {
        driver.navigate().to("https://automationexercise.com/");
        homePage.clickOnProducts();
        Thread.sleep(3000);
        closeAdPopupIfPresent1();
        Assert.assertEquals(productsPage.allProducts.getText(), "ALL PRODUCTS");
        productsPage.inputSearch("wintertop");
        productsPage.clickOnSearch();
        Assert.assertTrue(productsPage.products.stream().anyMatch(p -> p.getText().contains("Winter Top")));

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
