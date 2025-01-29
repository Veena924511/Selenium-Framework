package rahuklshettyacademy2.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	// we declared getReportObject() method in ExtentReport.java as static so we can
	// directly use that method
	// with classsname without creating object of the class
	ExtentReports extent = ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTestThread=new ThreadLocal<ExtentTest>(); //we need to store variable of type ExtentTest so used <ExtentTest> here

	@Override
	public void onTestStart(ITestResult result) {
		// we need to write createTest before executing method,
		// so you can write inside onTestStart mehod of listner
		// result variable holds all the information of the method that will be
		// executing(bcoz, before executing
		// method it will reach onTestStart first at that time result variable store
		// info about that method
		test = extent.createTest(result.getMethod().getMethodName());
		// result.getMethod().getMethodName() - gives test case name
		//ref .notes for below step
		extentTestThread.set(test);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// if test passed it will come here
		extentTestThread.get().log(Status.PASS, "test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// if test failed it will come here
		// test.log(Status.FAIL, "test passed");
		// we can write above line but we dont want to know test is failed but we want
		// to get error msg
		// so we are usng below line of code
		// to get the error msg
	
	extentTestThread.get().fail(result.getThrowable());
		try {
			// to assign life to the driver
			// result has all information about the class
			// getTestClass - return the class at run time
			// getRealClass - returns the original class
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

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
		// flush() is must - it make sure that extent reports are generated
		extent.flush();
	}

}
