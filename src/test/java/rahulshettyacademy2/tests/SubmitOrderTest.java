package rahulshettyacademy2.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahuklshettyacademy2.TestComponents.BaseTest;
import rahulshettyacademy2.AbstractComponents.AbstractComponenet;
import rahulshettyacademy2.PageObject.CartPage;
import rahulshettyacademy2.PageObject.ConfirmationPage;
import rahulshettyacademy2.PageObject.LandingPage;
import rahulshettyacademy2.PageObject.OrdersPage;
import rahulshettyacademy2.PageObject.PlaceOrder;
import rahulshettyacademy2.PageObject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
	
	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData",groups="purchase") //used group here, just to run only this method to check id DataProivder is working
	//public void submitOrder(String email,String password,String productName) throws IOException //- @DataProvider variables passed as an argument to method
	
	public void submitOrder(HashMap<String,String> input) throws IOException //we can retrieve the value using keys
	{	
		ProductCatalogue prdctCat=		landgPg.loginApplication(input.get("email"),input.get("password"));
		// wait unitil the elements are loaded
		// 2.add item to carts
		List<WebElement> products = prdctCat.getProductList();
		prdctCat.addToCart(input.get("productName"));

		CartPage cartPg =	prdctCat.gotToCartPage();
		// 3.check if item is added to cart and checkout
	
		Boolean match=cartPg.verifyProductDisplay(input.get("productName"));

		Assert.assertTrue(match);
		PlaceOrder plcOrder=	cartPg.checkOut();		
		// 4.place order
		plcOrder.selectCountry("india");
		ConfirmationPage cnfrmPg=	plcOrder.submitOrder();
		String confrmMsg=cnfrmPg.confrmMsg();
		Assert.assertTrue(confrmMsg.equalsIgnoreCase("Thankyou for the order."));
		
	}
	//click on Orders in the header and check if ordered item is present
	//this method should execute after placing the order, so we have added dependency submitOrder
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue prdctCat=		landgPg.loginApplication("****@gmail.com", "******");
		
		OrdersPage ordrsPg=prdctCat.goToOrderesPage();
	Assert.assertTrue(ordrsPg.verifyOrdersDisplay(productName));
	}
	//we can parameterize the test cases using @DataProvider annotation
	//Instead hard coding values in testcases(methods) we can pass it through dataprovider
	//we can pass our data to subsequent method using dataprovider=@DataProvider method name and pass the variable as parameter to the method
	//here, email,password,productName are the 3 parameters
	//here we have used 2D array with 2 sets of data so submitOrder() method will run twice
//	@DataProvider //using 2D array object
//	public Object[][] getData()
//	{
//		return new Object[][] {{"****@gmail.com","****","ADIDAS ORIGINAL"} ,{"******","*****","ADIDAS ORIGINAL"}};
//		
//	}
//ref.notes 
//	@DataProvider  //using Hashmap
//	public Object[][] getData()
//	{
//		//we can strore data in HashMap as key value pair
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "****@gmail.com");
//		map.put("password", "****");
//		map.put("productName","ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map1= new HashMap<String,String>();
//		map1.put("email", "******");
//		map1.put("password", "*****");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};
//		
//	}
	
	//ref.notes
	//using Json
	@DataProvider
	public Object[][] getData() throws IOException
	{
	
	List<HashMap<String,String>> data=	getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy2\\data\\purchaserOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}

