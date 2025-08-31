package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestUtils;

public class CartPage {
    private final WebDriver driver;
    private final TestUtils u;



    private final By proceedToCheckout = By.xpath("//a[contains(text(),'Proceed To Checkout')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

      public void clickProceedToCheckout() {
        u.click(proceedToCheckout);
    }
}
