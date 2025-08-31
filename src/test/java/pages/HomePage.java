package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestUtils;

public class HomePage {
    private final WebDriver driver;
    private final TestUtils u;

    private final By homeLogo = By.cssSelector("div.header-middle");
    private final By signupLogin = By.cssSelector("a[href='/login']");
    private final By productsBtn = By.cssSelector("a[href='/products']");
    private final By cartBtn = By.cssSelector("a[href='/view_cart']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

    public boolean isHomeVisible() {
        return u.isDisplayed(homeLogo);
    }

    public void clickSignupLogin() {
        u.click(signupLogin);
    }

    public void clickProducts() {
        u.click(productsBtn);
    }

    public void clickCart() {
        u.click(cartBtn);
    }
}
