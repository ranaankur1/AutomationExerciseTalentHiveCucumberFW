package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.*;
import utils.TestUtils;

public class Steps {

    private WebDriver driver = Hooks.driver;
    private TestUtils u = new TestUtils(driver);

    private HomePage home = new HomePage(driver);
    private LoginPage login = new LoginPage(driver);
    private SignupPage signup = new SignupPage(driver);
    private AccountPage account = new AccountPage(driver);
    private ProductsPage products = new ProductsPage(driver);
    private CartPage cart = new CartPage(driver);
    private CheckoutPage checkout = new CheckoutPage(driver);
    private PaymentPage payment = new PaymentPage(driver);

    @Given("User launches the browser and navigates to {string}")
    public void user_launches_browser_and_navigates_to(String url) {
        driver.get(url);
    }

    @When("User clicks Signup Login")
    public void user_clicks_signup_login() {
        home.clickSignupLogin();
    }

    @Then("New User Signup is visible")
    public void new_user_signup_is_visible() {
        Assert.assertTrue("New User Signup not visible", login.isNewUserSignupVisible());
    }

    @When("User enters signup name {string} and email {string}")
    public void user_enters_signup_name_and_email(String name, String email) {
        String uniqueEmail = email.replace("<random>", String.valueOf(System.currentTimeMillis()));
        signup.enterNameEmail(name, uniqueEmail);
    }

    @When("User clicks Signup button")
    public void user_clicks_signup_button() {
        signup.clickSignup();
    }

    @Then("Enter Account Information should be visible")
    public void enter_account_information_should_be_visible() {
        Assert.assertTrue("Enter account info not visible", signup.isEnterAccountInfoVisible());
    }

    @When("User fills account details with password {string} day {string} month {string} year {string}")
    public void user_fills_account_details_with_password_day_month_year(String pwd, String day, String month, String year) {
        signup.fillAccountDetails(pwd, day, month, year);
    }

    @When("User selects newsletters")
    public void user_selects_newsletters() {
        signup.selectNewsletters();
    }

    @When("User fills address {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void user_fills_address(String f, String l, String comp, String a1, String a2, String country, String state, String city, String zip, String mobile) {
        signup.fillAddress(f, l, comp, a1, a2, country, state, city, zip, mobile);
    }

    @When("User clicks Create Account")
    public void user_clicks_create_account() {
        signup.clickCreateAccount();
    }

    @Then("Account Created should be visible")
    public void account_created_should_be_visible() {
        Assert.assertTrue("Account Created!", account.isAccountCreatedVisible());
    }

    @When("User clicks Continue after account creation")
    public void user_clicks_continue_after_account_creation() {
        account.clickContinue();
    }

    @Then("User should be logged in as {string}")
    public void user_should_be_logged_in_as(String username) {
        Assert.assertTrue("Logged in as not visible", account.isLoggedInAsVisible(username));
    }

    @When("User opens Products")
    public void user_opens_products() {
        home.clickProducts();
    }

  
    @When("User add products to cart")
    public void user_add_products_to_cart() {
       
        products.addFirstProductToCart();
        products.clickContinueShopping();
    }

    @When("User clicks View Cart")
    public void user_clicks_view_cart() {
        products.clickViewCart();
    }

    @When("User proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cart.clickProceedToCheckout();
    }

    @Then("Address details and order review should be visible")
    public void address_details_and_order_review_should_be_visible() {
        Assert.assertTrue("Address/Review not visible", checkout.isAddressAndReviewVisible());
    }

    @When("User enters comment {string} and places order")
    public void user_enters_comment_and_places_order(String comment) {
        checkout.enterComment(comment);
        checkout.clickPlaceOrder();
    }

    @When("User enters payment details name {string} card {string} cvc {string} month {string} year {string}")
    public void user_enters_payment_details_name_card_cvc_month_year(String name, String card, String cvc, String month, String year) {
        payment.enterPaymentDetails(name, card, cvc, month, year);
    }

    @When("User clicks Pay and Confirm")
    public void user_clicks_pay_and_confirm() {
        payment.clickPayAndConfirm();
    }

    @Then("Order success message should be visible")
    public void order_success_message_should_be_visible() {
        Assert.assertEquals("Congratulations! Your order has been confirmed!", payment.isSuccessVisible());
    }

    @When("User deletes account")
    public void user_deletes_account() {
        account.clickDeleteAccount();
    }

    @Then("Account Deleted should be visible and continue")
    public void account_deleted_should_be_visible_and_continue() {
        Assert.assertTrue("Account deleted not visible", account.isAccountDeletedVisible());
        account.clickContinueAfterDelete();
    }
}
