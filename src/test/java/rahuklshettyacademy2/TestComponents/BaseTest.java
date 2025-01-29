package rahuklshettyacademy2.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy2.PageObject.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landgPg;
	public WebDriver initializeDriver() throws IOException {
		// Properties class in Selenium allows loading and retrieving values from
		// properties files
		Properties prop = new Properties();
		// give the file path as argument for FileInputStream
		//we cannot give our system path here,becuase if the same code runs in other system it will give error, so write System.getProperty("user.dir")- this will fetch current system path
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		// load() method is used to load the properties file, which we need to enter in
		// the form of FileInputStream
		prop.load(fis);
		// getProperty() method is used to retrive the value from properties file
		//ref.notes video. 181
		String browserName=System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			//to run in headless mode we need to use ChromeOptions
			ChromeOptions options=new ChromeOptions();
					 if(browserName.contains("headless"))
			 {
			 options.addArguments("headless");
			 }
			 driver = new ChromeDriver(options);
			
			 //when we run in headless some times windows display maximize properly, so run it in fullscreen
			 //we need to give it, but it is optional depends on our web page design
			 driver.manage().window().setSize(new Dimension(1440,900));
			
			
		}
		else if (browserName.contains("edge")) {
			 driver = new EdgeDriver();
			
				
		}

		else if (browserName.contains("firefox")) {

			driver=new FirefoxDriver();
			
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to String
		// pass json file path as an argument
		//StandardCharsets.UTF_8 -> means we want to convert our Json file in to string in  UTF_8 encoding format
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets	.UTF_8);
		// convert String to HashMap - for this we need to use Jackson Databind // dependency - search and add that dependency to pom.xml file
		ObjectMapper mapper = new ObjectMapper();
		// read String from jsonContent, convert it into HashMap and store it in list(we
		// used list because we have array of 2 indexes in json file which means we have
		// 2 HashMaps)
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		//convert driver to TakesScreenshot type
		TakesScreenshot ts = (TakesScreenshot)driver;
		//get the screenshot in file format
		File source=ts.getScreenshotAs(OutputType.FILE);
		//to store file in our project level folder
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png" ;
	
}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		 landgPg = new LandingPage(driver);

		landgPg.goTo();
		return landgPg;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	}

