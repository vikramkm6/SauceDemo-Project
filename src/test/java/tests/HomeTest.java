package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import driverFactory.BaseClass;

public class HomeTest extends BaseClass
{
	@Parameters({"username","password"})
    @Test(priority=1)
	public void checkLogOut(String username, String pwd)
	{
		
		test=extent.createTest("Logout Functionality check");
		log.do_login(username,pwd);
		test.pass(username + " is successfully landed on homepage");
		test.log(Status.INFO, username + " is navigated to menu bar to click logout option");
		try
		{
		home.click_logout();
		test.log(Status.PASS, username + " is successfully logout");
		}
		catch(Exception e)
		{
			test.log(Status.WARNING,username + " couldnt logout");
		
			test.fail(username + " didn't logout");
			test.fail("Exception handled" + e.getMessage());
			
		}
	
		
	}
	
}
