package bigProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import bigProject.ReusableComponents.AbstractComponent;

public class confirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public confirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css="[class='hero-primary']")
	WebElement successmessage;

	public String getConfirmationMessage()
	{
		return successmessage.getText();
	}
	
}
