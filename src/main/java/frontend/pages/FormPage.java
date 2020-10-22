package frontend.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {

    WebDriver driver;

    By nameInputBox = By.id("name");
    By countryInputBox = By.id("country");
    By cityInputBox = By.id("city");
    By cardInputBox = By.id("card");
    By monthInputBox = By.id("month");
    By yearInputBox = By.id("year");
    By purchaseButton = By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]");
    By okButton = By.xpath("/html/body/div[10]/div[7]/div/button");

    public FormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm() {
        driver.switchTo().activeElement();
        driver.findElement(nameInputBox).sendKeys("Anuj");
        driver.findElement(countryInputBox).sendKeys("India");
        driver.findElement(cityInputBox).sendKeys("Gurgaon");
        driver.findElement(cardInputBox).sendKeys("45452525215150000");
        driver.findElement(monthInputBox).sendKeys("12");
        driver.findElement(yearInputBox).sendKeys("2020");
        WebElement element = driver.findElement(purchaseButton);
        element.click();

        driver.switchTo().activeElement();
        WebElement okElement = driver.findElement(okButton);
        okElement.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOf(okElement));
    }
}
