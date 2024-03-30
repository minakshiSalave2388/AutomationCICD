package bigProject.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import bigProject.TestComponenets.baseTest;
import bigProject.pageObjects.CartPage;
import bigProject.pageObjects.ProductCatalouge;
import bigProject.pageObjects.checkoutPage;
import bigProject.pageObjects.confirmationPage;

public class ErrorValidation extends baseTest {
	
	//URL of ecommerce website "https://rahulshettyacademy.com/client/auth/login"
    //Credentials test1@coles.com, password- Testtest1$
	@Test(alwaysRun = true)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{	
		String email = "test1@coles.com";
		String password = "awesome";
		String productName = "ZARA COAT 3";
		
		//LandingPage	landingPage = LaunchApplication();
		ProductCatalouge prodCat = landingPage.LoginApplication(email, password);
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test(groups= {"ErrorHandling"},alwaysRun = true)
	public void ProductErrorValidation() throws IOException, InterruptedException
	{	
		String email = "test1@coles.com";
		String password = "Testtest1$";
		String productName = "ZARA COAT 3";
		
		ProductCatalouge prodCat = landingPage.LoginApplication(email, password);
		List<WebElement> products = prodCat.getProducts();
		prodCat.addProductToCart(productName);	
		CartPage cartPage= prodCat.goToCartPage();
		boolean match = cartPage.VerifyProductDisplay("Zara Coat 33");
		Assert.assertFalse(match);
		
		
	}

}
