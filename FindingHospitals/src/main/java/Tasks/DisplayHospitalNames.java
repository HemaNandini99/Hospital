package Tasks;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setup.ReadProperties;


public class DisplayHospitalNames {

	static WebDriver driver;
	Properties prop;
	WebDriverWait wait;
	String filePath = System.getProperty("user.dir") + "\\Resources\\properties\\config.properties";

	public DisplayHospitalNames() {

	}

	// Receiving the driver instance using constructor
	public DisplayHospitalNames(WebDriver driver) {
		DisplayHospitalNames.driver = driver;
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
	// for city
	public void selectLocation() {

		WebElement location = getElement(getPropertyValue("city"));

		location.clear();

		// wait
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getPropertyValue("city"))));

		// Enter location
		location.sendKeys("Bangalore");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getPropertyValue("cityname"))));
		getElement(getPropertyValue("cityname")).click();
	}

//Locate Hospital
	public void selectHospital() {
		WebElement type = getElement(getPropertyValue("Type"));
		type.sendKeys("Hospital");

		// Thread.sleep(3000); //wait //WebDriverWait wait1
		// =newWebDriverWait(driver,20);
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa-id='omni-searchbox-keyword']")));

		getElement(getPropertyValue("Typename")).click();
	}

	// Applying filters
	// open 24X7 hospitals
	public void applyFilters() {
		getElement(getPropertyValue("Open24X7")).click();
		
		// Has parking
		boolean staleElement = true;

		while (staleElement) {
			try {
				getElement(getPropertyValue("AllFiltersDropdown")).click();

				staleElement = false;
			} catch (StaleElementReferenceException e) {
				staleElement = true;
			}
		}
		WebElement parking=getElement(getPropertyValue("Parking"));
		parking.click();
	}

	public void hospitals() {
		
		
		  try { Thread.sleep(2000); } catch (InterruptedException e) {   e.printStackTrace(); }
		 System.out.println("***********HospitalNames****************");
		//wait.until(ExpectedConditions.visibilityOfAllElements(getElements(getPropertyValue("rating"))));
		List<WebElement> ratings = getElements(getPropertyValue("rating"));
		
	int n = ratings.size();
		
	
		for (int i = 0; i < n; i++) {
			String[] r = new String[n];
			//driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
			r[i] = ratings.get(i).getText();
			double value = Double.parseDouble(r[i].replaceAll("[^0-9]", ""));
			if (value > 3.5) {
				List<WebElement> name = getElements(getPropertyValue("HospitalName"));
				String hospitals = name.get(i).getText();
				System.out.println(hospitals);
			}

		}
		
	}
	public void Back() {
		//navigate to home 
				driver.navigate().back(); 
		}
	public static DisplayTopCities nextPage()
	{
		//sending driver to next page
		return PageFactory.initElements(driver, DisplayTopCities.class);
	}
}
