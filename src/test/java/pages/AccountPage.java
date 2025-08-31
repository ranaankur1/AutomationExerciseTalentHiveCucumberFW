package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestUtils;

public class AccountPage {
    private final WebDriver driver;
    private final TestUtils u;

    private final By accountCreated = By.xpath("//*[@id=\"form\"]/div/div/div/h2/b") ;
    private final By continueBtn = By.cssSelector("a[data-qa='continue-button']");
    private final By loggedInAs = By.cssSelector(".shop-menu");
    private final By deleteAccount = By.cssSelector("a[href='/delete_account']");
    private final By accountDeleted = By.xpath("//*[contains(text(),'Account Deleted') or contains(text(),'ACCOUNT DELETED!')]") ;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

    public boolean isAccountCreatedVisible() {
        return u.isDisplayed(accountCreated);
    }

    public void clickContinue() {
        u.click(continueBtn);
    }

    public boolean isLoggedInAsVisible(String username) {
        String txt = driver.findElement(loggedInAs).getText();
        return txt.contains("Logged in as " + username);
    }

    public void clickDeleteAccount() {
        u.click(deleteAccount);
    }

    public boolean isAccountDeletedVisible() {
        return u.isDisplayed(accountDeleted);
    }

    public void clickContinueAfterDelete() {
        u.click(continueBtn);
    }
}
