package frontend.pages;

import frontend.util.Wait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class HomePage {

    WebDriver driver;
    By phonesLink = By.linkText("Phones");
    By laptopsLink = By.linkText("Laptops");
    By monitorsLink = By.linkText("Monitors");
    By homeLink = By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a");
    By cartlink = By.xpath("//*[@id=\"cartur\"]");
    By productlink = By.className("card-title");
    By nextButton = By.id("next2");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo( String navigationTo) {
        switch (navigationTo.toUpperCase()){
            case "PHONES":
                driver.findElement(phonesLink).click();
                Wait.waitFor(5000);
                break;
            case "HOME":
                driver.findElement(homeLink).click();
                break;
            case "LAPTOPS":
                driver.findElement(laptopsLink).click();
                Wait.waitFor(5000);
                break;
            case "MONITORS":
                driver.findElement(monitorsLink).click();
                Wait.waitFor(5000);
                break;
            case "CART":
                driver.findElement(cartlink).click();
                break;
            default:
                driver.findElement(By.linkText(navigationTo)).click();
        }
    }

    public void clickProduct(String product) {
        List<WebElement> elements = driver.findElements(productlink);
        boolean found = false;
        for (WebElement element: elements){
            if(element.getText().equalsIgnoreCase(product)){
                element.click();
                found = true;
                break;
            }
        }

        if (!found) {
            List<WebElement> nextButtons = driver.findElements(nextButton);
            if (nextButtons==null || nextButtons.isEmpty()){
                Assert.fail(String.format("Product %s Not Found",product));
            }else {
                nextButtons.get(0).click();
                Wait.waitFor(2000);
                clickProduct(product);
            }
        }
    }
}
