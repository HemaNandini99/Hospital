package setup;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Tasks.DisplayHospitalNames;


public class Base {

	public static RemoteWebDriver driver;
	WebDriverWait wait;
	String filePath=System.getProperty("user.dir") + "\\Resources\\properties\\config.properties";
	Properties prop;
	
	
	
	public WebDriver openBrowser(String browserName)
	{
		//To Invoke the browser
		driver=DriverSetup.getWebDriver(browserName);
		driver.manage().window().maximize();
		prop=ReadProperties.readFile(filePath);
		wait=new WebDriverWait(driver, 120);
		openUrl();
		return driver;
	}
	public WebElement getElement(String path)
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
		return driver.findElement(By.xpath(path));
		
	}
	
	public String getPropertyValue(String key)
	{
		return prop.getProperty(key);
	}
	
	public void openUrl()
	{
		
		//To open the Url 
		driver.get(getPropertyValue("baseUrl"));
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		
	}
	
	
	public static DisplayHospitalNames nextPage()
	{
		//sending driver to next page
		return PageFactory.initElements(driver, DisplayHospitalNames.class);
	}
	
	
	public void closeBrowser()
	{
		//close the browser
		driver.quit();
	}
	

}
