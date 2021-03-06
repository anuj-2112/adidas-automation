package frontend.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {
    WebDriver driver;

    By productPanel = By.className("success");
    By placeOrder = By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button");
    By deleteLink = By.linkText("Delete");


    public CartPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void deleteProduct(String product) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(deleteLink)));
        List<WebElement> webElementList = driver.findElements(productPanel);
        boolean found = false;
        for (WebElement element : webElementList){
            if (element.getText().contains(product)){
                element.findElement(deleteLink).click();
                wait.until(ExpectedConditions.invisibilityOf(element));
                found = true;
                break;
            }
        }
        if (!found){
            Assert.fail(String.format("Product %s, is not found in Cart",product));
        }
    }

    public void placeOrder() {
        driver.findElement(placeOrder).click();
    }
}
