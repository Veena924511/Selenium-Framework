package rahulshettyacademy2.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy2.AbstractComponents.AbstractComponenet;

public class OrdersPage extends AbstractComponenet {
	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> OrdersList;

	// 3.check if item is present in the order
	public Boolean verifyOrdersDisplay(String productName) {

		Boolean match = OrdersList.stream().anyMatch(Orders -> Orders.getText().equalsIgnoreCase(productName));
		return match;

	}

	
}
