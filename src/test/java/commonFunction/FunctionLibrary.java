package commonFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import constant.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for login
		public static boolean verifyLogin(String username, String password) throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
			driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
			driver.findElement(By.xpath(config.getProperty("ObjLoginbtn"))).click();
			Thread.sleep(4000);
			String expected = "adminflow";
			String actual = driver.getCurrentUrl();
			if(actual.toLowerCase().contains(expected.toLowerCase()))
			{
				Reporter.log("Login Success",true);
				return true;
			}
			else
			{
				Reporter.log("Login fail",true);
				return false;
			}		
		}
		//method for branch click
		public static void clickBranch() throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("ObjClickBrances"))).click();
			Thread.sleep(3000);
		}
		//method for branch creation
		public static boolean verifyNewBranch(String BranchName,String Address1,String Address2,String Address3,String Area,String ZipCode,String Country,String State,String City) throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("ObjNewBranch"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(config.getProperty("ObjBranchName"))).sendKeys(BranchName);
			driver.findElement(By.xpath(config.getProperty("ObjAddress1"))).sendKeys(Address1);
			driver.findElement(By.xpath(config.getProperty("ObjAddress2"))).sendKeys(Address2);
			driver.findElement(By.xpath(config.getProperty("ObjAddress3"))).sendKeys(Address3);
			driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys(Area);
			driver.findElement(By.xpath(config.getProperty("Objzipcode"))).sendKeys(ZipCode);
			new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByVisibleText(Country);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath(config.getProperty("Objstate")))).selectByVisibleText(State);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath(config.getProperty("Objcity")))).selectByVisibleText(City);
			driver.findElement(By.xpath(config.getProperty("ObjSubmitbtn"))).click();
			Thread.sleep(2000);
			//capture alert
			String expectedBranchAlert = driver.switchTo().alert().getText();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			String actualalert = "New Branch";
			if(expectedBranchAlert.toLowerCase().contains(actualalert.toLowerCase()))
			{
				Reporter.log(expectedBranchAlert,true);
				return true;
			}
			else
			{
				Reporter.log("New Branch Creation Fail",true);
				return false;
			}

		}
		//method for branch update
		public static boolean verifyBranchUpdate(String BranchName,String Address,String ZipCode) throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
			Thread.sleep(3000);
			WebElement bname = driver.findElement(By.xpath(config.getProperty("ObjBranch")));
			bname.clear();
			bname.sendKeys(BranchName);
			WebElement add = driver.findElement(By.xpath(config.getProperty("ObjAddress")));
			add.clear();
			add.sendKeys(Address);
			WebElement zip = driver.findElement(By.xpath(config.getProperty("Objzip")));
			zip.clear();
			zip.sendKeys(ZipCode);
			Thread.sleep(3000);
			driver.findElement(By.xpath(config.getProperty("ObjUpdateBtn"))).click();
			Thread.sleep(3000);
			String actualupdatealert = "Branch updated";
			String expectedupdatealert= driver.switchTo().alert().getText();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			if(expectedupdatealert.toLowerCase().contains(actualupdatealert.toLowerCase()))
			{
				Reporter.log(expectedupdatealert,true);
				return true;
			}
			else
			{
				Reporter.log("Branch Update Fail",true);
				return false;
			}

		}
		//method for logout
		public static boolean verifyLogout()
		{
			driver.findElement(By.xpath(config.getProperty("ObjLogout"))).click();
			if(driver.findElement(By.xpath(config.getProperty("ObjLoginbtn"))).isDisplayed())
			{
				Reporter.log("Logout Success",true);
				return true;
			}
			else
			{
				Reporter.log("Logout Fail",true);
				return false;
			}
		}
}
