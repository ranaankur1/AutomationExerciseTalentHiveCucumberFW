package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.TestUtils;

public class ProductsPage {

    private final WebDriver driver;
    private final TestUtils u;

    private final By firstProductWrapper = By.cssSelector(".features_items .product-image-wrapper:nth-of-type(1)");
    private final By firstAdd = By.cssSelector(".features_items .product-image-wrapper:nth-of-type(1) a.add-to-cart");
    private final By continueShopping = By.cssSelector("button[class*='btn-success']");
    private final By viewCart = By.cssSelector("a[href='/view_cart']");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

    // Scroll, hover and click first product
    public void addFirstProductToCart() {
        waitAndHoverClick(firstProductWrapper, firstAdd);
    }

    // Click Continue Shopping button
    public void clickContinueShopping() {
        u.click(continueShopping);
    }

    // Click View Cart button
    public void clickViewCart() {
        u.click(viewCart);
    }



    // Handles wait, scroll, hover and click
    private void waitAndHoverClick(By wrapperLocator, By addLocator) {
        try {
        
            // Wait for wrapper visibility
            WebElement wrapper = u.waitVisible(wrapperLocator, 30);

            // Scroll into view
            u.scrollIntoView(wrapper);

           // Wait for Add button to be clickable and click
            WebElement addButton = u.waitClickable(addLocator, 15);
            TestUtils.click(driver, addButton);

        } catch (Exception e) {
            throw new RuntimeException("Failed to add product to cart: " + e.getMessage(), e);
        }
    }
}
