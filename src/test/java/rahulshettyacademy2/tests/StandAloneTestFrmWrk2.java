package rahulshettyacademy2.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy2.PageObject.LandingPage;

public class StandAloneTestFrmWrk2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String productName="ADIDAS ORIGINAL";
		driver.get("https://rahulshettyacademy.com/client");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
		//1.login
		driver.findElement(By.id("userEmail")).sendKeys("learnseleniumv@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Learn@999");
		driver.findElement(By.id("login")).click();
		// wait unitil the elements are loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		//2.add item to carts
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		// wait unitil toast appears i.e the loading signal after clicking on add to
		// cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// wait until product added to cart  animation disappears
		//if we use below wait the performance is slower.
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		//so we are using this wait which is little bit faster, where we just write invisibilityOf and pass entire web element inside
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		//3.check if item is added to cart and checkout
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match=	cartProducts.stream().anyMatch(cartProd->cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		//4.place order
		//driver.findElement(By.cssSelector(".form-group input")).click();
		//driver.findElement(By.cssSelector(".form-group input")).sendKeys("United");
		//List<WebElement> Countries=driver.findElements(By.cssSelector(".ta-item"));
		//WebElement selCountry=Countries.stream().filter(country->country.getText().equalsIgnoreCase("United Kingdom")).findFirst().orElse(null);
		//selCountry.click();
		//using Action class
	Actions a =new Actions(driver);
		
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confrmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confrmMsg.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		
	}
}
