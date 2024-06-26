package bigProject.TestComponenets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import bigProject.resources.ExtentReporterNG;

public class Listeners extends baseTest implements ITestListener{
	public static ExtentTest test;
	public ExtentReports extent = ExtentReporterNG.getReportObject();
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //threadsafe
	
	public void OnTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		//System.out.println(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "Test Pass");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		//Screenshot
		//test.log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		String filepath=null;
		try
		{
			filepath = getScreenshot(result.getMethod().getMethodName(),driver);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		
	}

	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}
