package rahulshettyacademy2.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy2.AbstractComponents.AbstractComponenet;

public class ProductCatalogue extends AbstractComponenet{
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy (css=".mb-3")
	List<WebElement> products;
	@FindBy (id="toast-container")
	WebElement spinner;
	 
	
	
	By wait=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMsg=By.cssSelector("#toast-container");

	// 2.add item to carts
	public List<WebElement> getProductList() 
	{
		waitForElementsToAppeear(wait);
		return products;
	}
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	public void addToCart(String productName) 
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementsToAppeear(toastMsg);
	
		//waitForElementsToDisappear(spinner);
		waitForElementsToDisappear(spinner);
	}
	
}
