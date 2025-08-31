package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.TestUtils;

public class SignupPage {
    private final WebDriver driver;
    private final TestUtils u;

    private final By name = By.name("name");
    private final By email = By.cssSelector("input[data-qa='signup-email']");
    private final By signupBtn = By.cssSelector("button[data-qa='signup-button']");

    private final By enterAccountInfo = By.xpath("//*[contains(text(),'Enter Account Information') or contains(text(),'ENTER ACCOUNT INFORMATION')]");    
    private final By titleMr = By.id("id_gender1");
    private final By password = By.id("password");
    private final By days = By.id("days");
    private final By months = By.id("months");
    private final By years = By.id("years");
    private final By newsletter = By.id("newsletter");
    private final By optin = By.id("optin");

    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobile = By.id("mobile_number");
    private final By createAccount = By.cssSelector("button[data-qa='create-account']");

    public SignupPage(org.openqa.selenium.WebDriver driver) {
        this.driver = driver;
        this.u = new TestUtils(driver);
    }

    public void enterNameEmail(String nm, String em) {
        u.type(name, nm);
        u.type(email, em);
    }

    public void clickSignup() {
        u.click(signupBtn);
    }

    public boolean isEnterAccountInfoVisible() {
        return u.isDisplayed(enterAccountInfo);
    }

    public void fillAccountDetails(String pwd, String day, String month, String yr) {
        u.click(titleMr);
        u.type(password, pwd);
        u.selectByVisibleText(days, day);
        u.selectByVisibleText(months, month);
        u.selectByVisibleText(years, yr);
    }

    public void selectNewsletters() {
        u.click(newsletter);
        u.click(optin);
    }

    public void fillAddress(String f, String l, String comp, String a1, String a2, String ctry, String st, String ct, String zip, String mob) {
        u.type(firstName, f);
        u.type(lastName, l);
        u.type(company, comp);
        u.type(address1, a1);
        u.type(address2, a2);
        u.selectByVisibleText(country, ctry);
        u.type(state, st);
        u.type(city, ct);
        u.type(zipcode, zip);
        u.type(mobile, mob);
    }

    public void clickCreateAccount() {
        u.click(createAccount);
    }
}
