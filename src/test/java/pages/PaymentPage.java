package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestUtils;

public class PaymentPage {
    private final WebDriver driver;
    private final TestUtils u;

    private final By nameOnCard = By.name("name_on_card");
    private final By cardNumber = By.name("card_number");
    private final By cvc = By.name("cvc");
    private final By expiryMonth = By.name("expiry_month");
    private final By expiryYear = By.name("expiry_year");
    private final By payButton = By.id("submit");
    private final By successMsg = By.xpath("//*[contains(text(),'order has been placed successfully') or contains(text(),'Your order has been placed successfully!')]") ;
    private final By successMsgOrderconfirm = By.xpath("//*[contains(text(),'Congratulations! Your order has been confirmed!')]") ;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

    public void enterPaymentDetails(String name, String card, String cv, String mon, String yr) {
        u.type(nameOnCard, name);
        u.type(cardNumber, card);
        u.type(cvc, cv);
        u.type(expiryMonth, mon);
        u.type(expiryYear, yr);
    }

    public void clickPayAndConfirm() {
        u.click(payButton);
    }

    public String isSuccessVisible() {
        return u.getText(successMsgOrderconfirm);
    }
}
