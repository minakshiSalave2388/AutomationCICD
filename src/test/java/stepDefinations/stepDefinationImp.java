package stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bigProject.TestComponenets.baseTest;
import bigProject.pageObjects.CartPage;
import bigProject.pageObjects.LandingPage;
import bigProject.pageObjects.ProductCatalouge;
import bigProject.pageObjects.checkoutPage;
import bigProject.pageObjects.confirmationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class stepDefinationImp extends baseTest {
	public LandingPage landingPage;
	public ProductCatalouge prodCat;
	public confirmationPage confPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = LaunchApplication();
	}
	
	@Given("^Loggin with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password)
	{
		prodCat = landingPage.LoginApplication(username, password);
	}
	
	@When("^I add product (.+) from cart$")
	public void add_product(String productName) throws InterruptedException 
	{
		List<WebElement> products = prodCat.getProducts();
		prodCat.addProductToCart(productName);	
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage= prodCat.goToCartPage();
		boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		checkoutPage checkout = cartPage.goToCheckout();
		checkout.selectCountry("India");
		confPage = checkout.submitOrder();
	}
	
	@Then("^(.+) message is displayed on confirmation page$")
	public void displayed_message(String message)
	{
		String successmessage = confPage.getConfirmationMessage();
		Assert.assertTrue(successmessage.equalsIgnoreCase(message));
	}

}
