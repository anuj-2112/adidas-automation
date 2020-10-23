package stepdefs.frontend;

import backend.application.Constants;
import frontend.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class DemoBlazeStepDefs {

    WebDriver driver;

    @Before
    public void beforeTest(){
        //initiate driver before each test
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("Demo Blaze URL is open")
    public void demoBlazeURLIsOpen() {
        driver.get(Constants.demoBlazeURL);
    }

    @After
    public void afterTest(){
        //Quit driver after each test
        driver.quit();
    }

    @When("Navigate to {}")
    public void navigate(String navigationTo) {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo( navigationTo);
    }

    @When("Product is added To Cart")
    public void productIsAddedToCart() {
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
    }

    @And("Accept Pop Up")
    public void acceptPopUp() {
        CommonPage commonPage = new CommonPage(driver);
        commonPage.acceptAlert();
    }

    @When("Product {} is clicked")
    public void productClick(String product) {
        HomePage homePage = new HomePage(driver);
        homePage.clickProduct(product);

    }

    @And("Delete {} from the cart")
    public void deleteDellIGbFromTheCart(String product) {
        CartPage cartPage = new CartPage(driver);
        cartPage.deleteProduct(product);
    }

    @Then("Place Order")
    public void placeOrder() {
        CartPage cartPage = new CartPage(driver);
        cartPage.placeOrder();
    }

    @And("Enter Details into the Form")
    public void enterDetailsIntoTheForm() {
        FormPage formPage = new FormPage(driver);
        formPage.fillForm();
    }
}
