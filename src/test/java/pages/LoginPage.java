package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestUtils;

public class LoginPage {
    private final WebDriver driver;
    private final TestUtils u;

    private final By newUserSignup = By.xpath("//h2[text()='New User Signup!']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

    public boolean isNewUserSignupVisible() {
        return u.isDisplayed(newUserSignup);
    }
}
