package bigProject.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import bigProject.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fst = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//bigProject//resources//GlobalData.properties");
	
		prop.load(fst);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser"):prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
				{
					ChromeOptions option = new ChromeOptions();		
					WebDriverManager.chromedriver().setup();
					if(browserName.contains("headless"))
						{
							option.addArguments("headless");
						}
					driver = new ChromeDriver(option);
					driver.manage().window().fullscreen();
					
				}
		else if(browserName.equalsIgnoreCase("firefox"))
				{
					driver = new FirefoxDriver();
				}
		else if(browserName.equalsIgnoreCase("edge"))
				{
					driver = new EdgeDriver();
				}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
		{
			driver = initializeDriver();
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("User.dir")+"//reports"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("User.dir")+"//reports"+testCaseName+".png";
	}

}
