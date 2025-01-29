package rahulshettyacademy2.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahuklshettyacademy2.TestComponents.Retry;
//import com.sun.net.httpserver.Authenticator.Retry;

import rahuklshettyacademy2.TestComponents.BaseTest;
import rahulshettyacademy2.PageObject.CartPage;
import rahulshettyacademy2.PageObject.ConfirmationPage;
import rahulshettyacademy2.PageObject.PlaceOrder;
import rahulshettyacademy2.PageObject.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	@Test(groups= {"ErrorValidation"}, retryAnalyzer=rahuklshettyacademy2.TestComponents.Retry.class)
	public void loginErrorValidation() 
	{
		
			landgPg.loginApplication("learnseleniumv@gmail.com", "Learn@999");
		Assert.assertEquals("Incorrect email  password.",landgPg.getErrorMsg());
	}
	@Test
	public void productErrorValidation() throws IOException
	{
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue prdctCat=		landgPg.loginApplication("learnseleniumv@gmail.com", "Learn@999");
		List<WebElement> products = prdctCat.getProductList();
		prdctCat.addToCart(productName);
		CartPage cartPg =	prdctCat.gotToCartPage();
		Boolean match=cartPg.verifyProductDisplay("ADIDAS ORIGINAL123");
		Assert.assertFalse(match);
		

	}

}
