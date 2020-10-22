package frontend.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage {

    WebDriver driver;
    public CommonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptAlert() {
        String mainWindow=driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        driver.switchTo().window(mainWindow);
    }
}
