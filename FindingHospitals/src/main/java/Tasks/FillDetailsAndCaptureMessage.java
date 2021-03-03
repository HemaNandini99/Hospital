package Tasks;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import setup.ReadProperties;

public class FillDetailsAndCaptureMessage {
	static WebDriver driver;
	Properties prop;
	WebDriverWait wait;
	String filePath = System.getProperty("user.dir") + "\\Resources\\properties\\config.properties";

	public FillDetailsAndCaptureMessage() {

	}

	// Receiving the driver instance using constructor
	public FillDetailsAndCaptureMessage(WebDriver driver) {
		FillDetailsAndCaptureMessage.driver = driver;
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

	public void Back() {
		// navigate to home
		driver.navigate().back();
	}

	public void selectCorporateWellness() {
		// select corporate wellness from providers drop down
		getElement(getPropertyValue("ForProvidersDropdown")).click();
		getElement(getPropertyValue("CorporateWellness")).click();

	}

	public void windows() {
		// Handling windows
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();

		String mainpage = it.next();
		String childpage = it.next();

		// move to new window
		driver.switchTo().window(childpage);
	}

	public void fillform() {
		// enter the details
		driver.findElement(By.id("name")).sendKeys("ram");
		driver.findElement(By.id("organization_name")).sendKeys("ramesh");
		driver.findElement(By.id("official_email_id")).sendKeys("ram123");
		driver.findElement(By.id("official_phone_no")).sendKeys("456");
		driver.findElement(By.id("button-style")).click();
	}

	public void handleAlert() {
		// move to alert and display the message
		Alert alt = driver.switchTo().alert();
		System.out.println("Alert message: " + alt.getText());
		alt.accept();

	}

	public void imageCapture() {
		// capturing the image
		try {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenShotPath;
			FileHandler.copy(screenshot, new File(screenShotPath = ".\\src\\screenshot.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
