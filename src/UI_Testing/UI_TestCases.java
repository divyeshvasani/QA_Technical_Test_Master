package UI_Testing;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UI_TestCases {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", "E:/Automation (Selenium)/Workspace/Drivers/chromedriver.exe");
		driver = new ChromeDriver();		// Launch Chrome Browser
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:5000/v1/ui/");
	}
	
	
	@Test(priority=1,groups="Web Page")
	public void PageTitle_Test() 
	{
		String page_title = driver.getTitle();
		Assert.assertEquals(page_title, "Swagger UI");													 // Validate Web Page		
		System.out.println("Page Title = "+page_title);									   		  	    // Get Page Title
	}
	
	
	@Test(priority=2,groups="Logo")
	public void Logo_Test()
	{	
		boolean logo_image = driver.findElement(By.xpath("//a[@id='logo']")).isDisplayed(); 
		Assert.assertEquals(logo_image, true);												   	       // Validate Logo Image
		System.out.println("Logo Image is Present");												  // Get Logo Image Status		
	
		driver.findElement(By.xpath("//a[@id='logo']")).click();
		String page_URL = driver.getCurrentUrl();
		Assert.assertEquals(page_URL, "https://swagger.io/");										// Validate Web Page URL		
		System.out.println("Logo link is working");											 	   // Link Status		
	}
	
	
	@Test(priority=3,groups="Explore")
	public void Explore_Test()
	{	
		driver.findElement(By.xpath("//input[@id='input_baseUrl']")).sendKeys("http://localhost:5000/v1/swagger.json");
		driver.findElement(By.xpath("//input[@id='input_apiKey']")).sendKeys("api_key");
		driver.findElement(By.xpath("//a[@id='explore']")).click();
		String page_title = driver.getTitle();
		Assert.assertEquals(page_title, "Swagger UI");											// Validate Web Page After Explore
		System.out.println("Page Title = "+page_title);										   // Get Page Title After Explore
	}
	
	
	@Test(priority=4,groups="Links")
	public void default_Link_Test()
	{	
		driver.findElement(By.xpath("//a[contains(text(),'default')]")).click();
		String page_default_URL = driver.getCurrentUrl();
		Assert.assertEquals(page_default_URL, "http://localhost:5000/v1/ui/#/default");		// Validate Web Page URL		
		System.out.println("default link is working");									   // Link Status
	}
	
	
	@Test(priority=5,groups="Links")
	public void Show_Hide_Test()
	{	
		driver.findElement(By.xpath("//a[@id='endpointListTogger_default']")).click();
		String page_show_URL = driver.getCurrentUrl();
		Assert.assertEquals(page_show_URL, "http://localhost:5000/v1/ui/#/default");	 // Validate Web Page URL		
		System.out.println("Show Link is working");										// Link Status
		
		driver.findElement(By.xpath("//a[@id='endpointListTogger_default']")).click();
		String page_hide_URL = driver.getCurrentUrl();
		Assert.assertEquals(page_hide_URL, "http://localhost:5000/v1/ui/#/");		  // Validate Web Page URL		
		System.out.println("Hide Link is working");									 // Link Status
		
	}
	
	
	@Test(priority=6,groups="Links")
	public void List_Operation_Link_Test()
	{	
		driver.findElement(By.xpath("//a[@class='collapseResource']")).click();
		String text = driver.findElement(By.xpath("//a[contains(text(),'delete')]")).getTagName();
		Assert.assertEquals(text, "a");												// Validate Text		
		System.out.println("List Operation link is working");			   		   // Link Status
	}
	
	
	@Test(priority=7,groups="Links")
	public void Expand_Operation_Link_Test()
	{	
		driver.findElement(By.xpath("//a[@class='expandResource']")).click();
		String text = driver.findElement(By.xpath("//a[contains(text(),'delete')]")).getTagName();
		Assert.assertEquals(text, "a");											// Validate Text		
		System.out.println("Expand Operation link is working");			   	   // Link Status
	}
	
	
	@AfterMethod
	public void tearDown() 
	{
		driver.quit();
	}
	
	
}
