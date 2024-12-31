package Amazon.seleniumFrameworkProject.TestComponent;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v129.page.model.Screenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import globalData.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{

	public static ExtentTest test ; 
	ExtentReports extent = ExtentReporterNG.config() ;
	ThreadLocal <ExtentTest>extentTest = new ThreadLocal<ExtentTest>();
	

	@Override
	public void onTestStart(ITestResult result) {
		
	test = 	extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.get().log(Status.PASS, "Test Passed") ;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//Screenshot
		//test.log(Status.FAIL, "Test Failed");
	//	extent.startTest();
	//	extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		
		try {
		String filePath = 	getScreenShot(result.getMethod().getMethodName(),driver);
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
