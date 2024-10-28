package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import driverFactory.BaseClass;
import excelReader.ExcelRead;
import utilities.Screenshot;

public class LoginTest extends BaseClass {

	@Test(priority = 0, dataProviderClass = ExcelRead.class, dataProvider = "fetchData")
	public void checkLogin(String username, String password) throws InterruptedException {
       
		log.do_login(username, password);
		
		if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
			test = extent.createTest("Login Functionality check with valid user");
			test.pass(username + " is on home page successfully");
			Assert.assertTrue(true);

		}

		else

		{
			test = extent.createTest("Login functionality check with invalid user");
			test.fail(username + " User unable to reach homepage");
			

			
		}

	}

}
