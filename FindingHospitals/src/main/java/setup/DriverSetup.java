package setup;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSetup {
	
	public static RemoteWebDriver getWebDriver(String browserName)
	{
		RemoteWebDriver driver = null;
		DesiredCapabilities dc = null;
		if(browserName.equalsIgnoreCase("chrome"))
		{
			dc = DesiredCapabilities.chrome();
		}else if(browserName.equalsIgnoreCase("edge"))
		{
			dc = DesiredCapabilities.edge();
		}else if(browserName.equalsIgnoreCase("opera"))
		{
			dc = DesiredCapabilities.opera();	
		}else if(browserName.equalsIgnoreCase("firefox"))
		{
			dc = DesiredCapabilities.firefox();
		}
		
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		return driver;
	}
}
