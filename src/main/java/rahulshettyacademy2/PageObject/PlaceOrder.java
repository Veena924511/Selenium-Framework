package rahulshettyacademy2.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy2.AbstractComponents.AbstractComponenet;

public class PlaceOrder extends AbstractComponenet{
	WebDriver driver;
	public PlaceOrder(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[placeholder='Select Country']")
private	WebElement country;
	@FindBy (xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement countryClick;
	@FindBy (css=".action__submit")
	private	WebElement submitOrder;
	@FindBy(css=".hero-primary")
	private	WebElement ConfrmMsg;
	
	
	By wait=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementsToAppeear(wait);
		countryClick.click();
	}
public ConfirmationPage submitOrder()
{
	submitOrder.click();
	return new ConfirmationPage(driver);
}

}
