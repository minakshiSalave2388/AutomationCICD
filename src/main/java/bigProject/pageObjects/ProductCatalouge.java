package bigProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bigProject.ReusableComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {
	
	WebDriver driver;
	public ProductCatalouge(WebDriver driver) {	
		//Initialisation code
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	//page factory
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	@FindBy(id="toast-container")
	WebElement container;

	
	By containerBy =By.id("toast-container");
	
	By productsBy = By.cssSelector(".col-lg-4");
	
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	//By addToCart =By.cssSelector("[routerlink*='/dashboard/cart']");
	
	public List<WebElement> getProducts()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProducts().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button[class='btn w-10 rounded']"));
		
		return prod;
		
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(containerBy);
		waitForElementToDisappear();
		
	}
}
	