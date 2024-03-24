package bigProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import bigProject.ReusableComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {	
		//Initialisation code
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='cartSection']/h3")
	List<WebElement> prodlist;
	
	@FindBy(css=".totalRow button")
	WebElement totalRowButton;
	
	
	//@FindBy(css="button[routerlink*='/dashboard/cart']")
	//WebElement clickCartButton;
	
	public boolean VerifyProductDisplay(String productName)
	{

		boolean match= prodlist.stream().anyMatch(product->product.getText().equals(productName));
		return match;
	}
	
	public checkoutPage goToCheckout()
	{
		totalRowButton.click();
		//clickCartButton.click();
		return new checkoutPage(driver);
	}
	
}
	