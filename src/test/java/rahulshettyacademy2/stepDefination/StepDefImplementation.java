package rahulshettyacademy2.stepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahuklshettyacademy2.TestComponents.BaseTest;
import rahulshettyacademy2.PageObject.CartPage;
import rahulshettyacademy2.PageObject.ConfirmationPage;
import rahulshettyacademy2.PageObject.LandingPage;
import rahulshettyacademy2.PageObject.PlaceOrder;
import rahulshettyacademy2.PageObject.ProductCatalogue;

public class StepDefImplementation extends BaseTest{
	public LandingPage landgPage;
	public ProductCatalogue prdctCat;
	public ConfirmationPage cnfrmPg;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landgPage=launchApplication();
	}
	 @Given ("^Logged in with userName (.+) and password (.+)$")
	 public void Logged_in_with_username_and_password(String userName, String password)
	 {
		 prdctCat=landgPg.loginApplication(userName,password);
	 }
	 @When ("^I add product (.+) to cart$")
	 public void I_add_product_to_cart(String productName)
	 {
			List<WebElement> products = prdctCat.getProductList();
			prdctCat.addToCart(productName);
	 }
	
	 @And ("^checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName)
	 {
		 	CartPage cartPg =	prdctCat.gotToCartPage();
			Boolean match=cartPg.verifyProductDisplay(productName);
			Assert.assertTrue(match);
			PlaceOrder plcOrder=	cartPg.checkOut();		
			plcOrder.selectCountry("india");
			 cnfrmPg=	plcOrder.submitOrder();
	 }
	 @Then ("{string} message is displayed on confirmationPage")
	 public void message_is_displayed_on_confirmationPage(String string)
	 {
			String confrmMsg=cnfrmPg.confrmMsg();
			Assert.assertTrue(confrmMsg.equalsIgnoreCase(string));
			driver.close();
	 }
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String string)
	 {
			Assert.assertEquals(string,landgPg.getErrorMsg());
			driver.close();
	 }
	 
}
