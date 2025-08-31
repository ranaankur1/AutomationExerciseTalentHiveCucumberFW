package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestUtils;

public class CheckoutPage {
    private final WebDriver driver;
    private final TestUtils u;

    private final By addressDelivery = By.cssSelector("#address_delivery");
    private final By reviewOrder = By.cssSelector(".checkout-information, #cart_info");
    private final By commentBox = By.name("message");
    private final By placeOrder = By.xpath("//a[contains(@class,'btn btn-default check_out')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

    public boolean isAddressAndReviewVisible() {
        return u.isDisplayed(addressDelivery) && u.isDisplayed(reviewOrder);
    }

    public void enterComment(String comment) {
        u.type(commentBox, comment);
    }

    public void clickPlaceOrder() {
        u.click(placeOrder);
    }
}
