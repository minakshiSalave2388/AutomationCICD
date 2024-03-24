package bigProject.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import bigProject.TestComponenets.baseTest;
import bigProject.pageObjects.CartPage;
import bigProject.pageObjects.OrderPage;
import bigProject.pageObjects.ProductCatalouge;
import bigProject.pageObjects.checkoutPage;
import bigProject.pageObjects.confirmationPage;

public class SubmitOrderTest extends baseTest {
	
	//String email ;
	//String password;
	//String productName;
	
	//URL of ecommerce website "https://rahulshettyacademy.com/client/auth/login"
    //Credentials test1@coles.com, password- Testtest1$
	
	@Test(dataProvider="getData", groups="Purchase",alwaysRun = true)
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{			
		ProductCatalouge prodCat = landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = prodCat.getProducts();
		prodCat.addProductToCart(input.get("productName"));	
		CartPage cartPage= prodCat.goToCartPage();
		boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		checkoutPage checkout = cartPage.goToCheckout();
		checkout.selectCountry("India");
		confirmationPage confPage = checkout.submitOrder();
		String successmessage = confPage.getConfirmationMessage();
		Assert.assertTrue(successmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	//To verify ZARA Coat 3 is displayed in order page
	@Test(dataProvider="getData1",dependsOnMethods= {"SubmitOrder"},alwaysRun = true)
	public void OrderHistoryTest(HashMap<String,String> input)
	{
		
		ProductCatalouge prodCat = landingPage.LoginApplication(input.get("email"), input.get("password"));
		OrderPage orderpage= prodCat.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(input.get("productName")));
	}
	

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
			//Convert String to hashmap using Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		
		return data;
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\bigProject\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};	
	}
	

	@DataProvider
	public Object[][] getData1() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\bigProject\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};	
	}
	//@DataProvider
	/*public Object[][] getData() throws IOException
	{
		HashMap<String,String> map= new HashMap<String,String>();
		map.put("email", "test1@coles.com");
		map.put("password", "Testtest1$");
		map.put("productName","ZARA COAT 3");
		
		HashMap<String,String> map1= new HashMap<String,String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("productName", "ADIDAS ORIGINAL");	
		
	}*/
	
	//@DataProvider
	//public Object[][] getData()
	//{	
	//	return new Object[][] {{"test1@coles.com","Testtest1$","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	//}
}
