package constant;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {
	public static WebDriver driver;
	public static Properties config;
	@BeforeSuite
	public void setup() throws Throwable
	{
		config = new Properties();
		config.load(new FileInputStream("C:\\Users\\RAKY AL0NE\\eclipse-workspace\\MyMavenProject\\PropertyFiles\\Primus.properties"));
		if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(config.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
		System.setProperty("webdriver.gecko.driver", "d:/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.get(config.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	else
	{
		Reporter.log("browser not matching",true);
	}
	}
	@AfterSuite
	public void tearDown()
	{
		driver.close();
	}

}
