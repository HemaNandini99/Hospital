package Tasks;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setup.ReadProperties;

public class DisplayTopCities {
	
	static WebDriver driver;
	Properties prop;
	WebDriverWait wait;
	String filePath = System.getProperty("user.dir") + "\\Resources\\properties\\config.properties";

	public DisplayTopCities() {

	}

	// Receiving the driver instance using constructor
	public DisplayTopCities(WebDriver driver) {
		DisplayTopCities.driver = driver;
		prop = ReadProperties.readFile(filePath);
		wait = new WebDriverWait(driver, 10);

	}
	// Getting the values for given key from property file
		public String getPropertyValue(String key) {
			return prop.getProperty(key);
		}

		// locating the element
		public WebElement getElement(String path) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
			return driver.findElement(By.xpath(path));
		}
		public List<WebElement> getElements(String path) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
			return driver.findElements(By.xpath(path));
		}
	
	public void Back() {
	//navigate to home 
			driver.navigate().back(); 
	}
			// click on diagnostics
	public void clickOnDiagnostics() {
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		//wait.until(ExpectedConditions.titleContains("Best Hospitals in Bangalore - Book Appointment Online"));
		getElement(getPropertyValue("Diagnostics")).click();

	}
	public void topcities() {
		// display top cities 
		System.out.println("***********Top cities****************"); 
				//wait.until(ExpectedConditions.titleContains("Blood Tests"));
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		List<WebElement>
				topcities = getElements(getPropertyValue("TopCities"));
				for (WebElement tc : topcities) {
					String cityname = tc.getText();
					System.out.println(cityname);
				}

	}
	public static FillDetailsAndCaptureMessage nextPage()
	{
		//sending driver to next page
		return PageFactory.initElements(driver, FillDetailsAndCaptureMessage.class);
	}
	
			
}
