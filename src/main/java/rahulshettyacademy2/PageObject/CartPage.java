package rahulshettyacademy2.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy2.AbstractComponents.AbstractComponenet;

public class CartPage extends AbstractComponenet {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
 
	// PageFactory

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement placeOrder;

	// 3.check if item is added to cart and checkout
	public Boolean verifyProductDisplay(String productName) {

		Boolean match = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;

	}
	public PlaceOrder checkOut()
	{
		placeOrder.click();
		return new PlaceOrder(driver);
	}
}
