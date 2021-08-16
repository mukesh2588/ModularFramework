package commonLibs.implementation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CommonDriver {
	private WebDriver driver;
	
	private int pageLoadTimeout;
	
	private int elementDetectionTimeput;
	
	private String currentWorkingDirectory;
	
	public CommonDriver(String browserType) throws Exception
	{
		pageLoadTimeout=10;
		currentWorkingDirectory=System.getProperty("user.dir");
		if(browserType.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory+"/drivers/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browserType.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory+"/drivers/msedgedriver.exe");
			driver=new EdgeDriver();
		}
		else
		{
			throw new Exception("Invalid browser type" +browserType);
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public void navigateToUrl(String url)
	{
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeput, TimeUnit.SECONDS);
		
		driver.get(url);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout) {
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetectionTimeput(int elementDetectionTimeput) {
		this.elementDetectionTimeput = elementDetectionTimeput;
	}
	public void closeAllBrowser() {
		driver.quit();
	}
	public String getTitleOfThePage()
	{
		return driver.getTitle();
	}

}
