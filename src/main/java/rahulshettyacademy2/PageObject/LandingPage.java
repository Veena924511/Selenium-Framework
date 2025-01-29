package rahulshettyacademy2.PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy2.AbstractComponents.AbstractComponenet;

public class LandingPage extends AbstractComponenet{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMsg;

	// 1.login
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue prdctCat = new ProductCatalogue(driver);
		return prdctCat;
	}
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMsg()
	{
		waitForWebElementsToAppeear(errorMsg);
		return errorMsg.getText();
	}
}
