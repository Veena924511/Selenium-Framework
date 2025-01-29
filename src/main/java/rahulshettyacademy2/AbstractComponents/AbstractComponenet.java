package rahulshettyacademy2.AbstractComponents;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy2.PageObject.CartPage;
import rahulshettyacademy2.PageObject.OrdersPage;

public class AbstractComponenet {
	WebDriver driver;
	
	@FindBy (css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	
	
	public AbstractComponenet(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementsToAppeear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementsToAppeear(WebElement findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementsToDisappear(WebElement ele) 
	{
		//Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	//click on the cart symbol..which we repeat multiple time..so written in this page
	public CartPage gotToCartPage()
	{
		cartHeader.click();
		CartPage cartPg = new CartPage(driver);
		return cartPg;
	}
	//click on orders symbol ..to check ordered item present ..which we repeat multiple time..so written in this page
	public OrdersPage goToOrderesPage()
	{
		ordersHeader.click();
		return new OrdersPage(driver);
	}
}
