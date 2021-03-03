package TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import Tasks.DisplayHospitalNames;
import Tasks.DisplayTopCities;
import Tasks.FillDetailsAndCaptureMessage;
import setup.Base;


public class RunTestCases {

	@BeforeTest
	@Parameters("browser")
	public void openBrowserTab(String browser)
	{
		Base bs=new Base();
		//String browser="chrome";
		bs.openBrowser(browser);
	}
	
	@AfterTest
	public void closeBrowserWindow()
	{
		Base bs=new Base();
		bs.closeBrowser();
	}
	@Test(priority = 0 )
	public void PrintHospitals()
	{
		DisplayHospitalNames hp=Base.nextPage();
		hp.selectLocation();
		hp.selectHospital();
		hp.applyFilters();
		hp.hospitals();
		hp.Back();
		
	}
	
	@Test(priority = 1 , dependsOnMethods = {"PrintHospitals"})
	public void PrintTopCities()
	{
		DisplayTopCities top=DisplayHospitalNames.nextPage();
		top.Back();
		top.clickOnDiagnostics();
		top.topcities();
	}
	
	@Test(priority = 2, dependsOnMethods = {"PrintTopCities"})
	public void CaptureMessage(){
		FillDetailsAndCaptureMessage msg=DisplayTopCities.nextPage();
		msg.Back();
		msg.selectCorporateWellness();
		msg.windows();
		msg.fillform();
		msg.handleAlert();
		msg.imageCapture();
	}
	
	
	
}
