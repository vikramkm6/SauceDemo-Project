package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import driverFactory.BaseClass;
import excelReader.ExcelRead;

public class CheckOutTest extends BaseClass {

	@Test( dataProviderClass = ExcelRead.class, dataProvider = "fetchData", priority = 7)
	public void checkOut(String uname, String pwd) {
		log.do_login(uname, pwd);
		test = extent.createTest("Validation for user checkout page");

		try {
			add.addAllProds();
			String checkOut = check.clickcheckOut();

			if (checkOut.equals("Checkout: Overview")) {
				test.pass(uname + " is on checkout page successfully");
				Assert.assertTrue(true);
			} else {
				test.fail(uname + " is not on checkout page ");
			
			}
		} catch (Exception e) {
			test.fail(uname + " couldn't reach Checkout page");
			Assert.assertTrue(false);
		}
	}

	@Test(dataProviderClass = ExcelRead.class, dataProvider = "fetchData", priority = 8)
	public void checkTotal_Prod_WithCartItems(String uname, String pwd) throws InterruptedException {
		log.do_login(uname, pwd);
		test = extent.createTest("Validating the Consolidation of product price with Total price");

		try {
			add.addAllProds();
			check.clickcheckOut();
			Thread.sleep(2000);
			double sumOfProducts = check.getTotalProducts();
			System.out.println(uname + "sum of products :" + sumOfProducts);

			double summary_total = check.getTotal();
			System.out.println(uname + "Total value of product page is :" + summary_total);

			if (summary_total == sumOfProducts) {
				test.pass(uname + " : cart products consolidation matched with total value i.e " + summary_total + "="
						+ sumOfProducts);
				Assert.assertTrue(true);
			} else {
				test.fail(uname + " cart products consolidation doesnt match i.e: Total price on page is : "
						+ summary_total + "Added price on page is =" + sumOfProducts);
			
			}
		} catch (Exception e) {
			Thread.sleep(2000);
			test.fail(uname + " couldn't reach Checkout page");
			Assert.assertTrue(false);
		}

	}
	
	@Test(priority = 9,dataProviderClass = ExcelRead.class, dataProvider = "fetchData")
	public void checkFieldInformation(String uname, String pwd) throws InterruptedException
	{
		log.do_login(uname, pwd);
		test=extent.createTest(uname + " check the mandatory field error message");
		
		try
		{
		add.addAllProds();
		test.info(uname + " adding all the items to the cart");
		Thread.sleep(2000);
	    
		String actualerror_Msg = check.leavingMandatoryFields();
		test.info(uname + " leaving the mandatory field informations to be blank like Firstname , Last name and Postal code");
		test.info(uname + " capturing the error message");
		String expectedErrorMsg= "Error: First Name is required";
		if(actualerror_Msg.equals(expectedErrorMsg))
		{
			test.pass(uname + " getting an error message as :  " +expectedErrorMsg);
			Assert.assertTrue(true);
		}
		else
		{
			test.fail(uname + " not validating an error message ");
		
		}
		}
		catch(Exception e)
		{
			test.warning(uname + " never reach to checkout page");
			test.fail(uname + " couldn't reach to field information ");
			
			Assert.assertTrue(false);
		}
		
		
	}

	@Test(priority=10,dataProviderClass = ExcelRead.class, dataProvider = "fetchData")
	public void checkOrderCompletion(String uname , String pwd) throws InterruptedException
	{
		log.do_login(uname, pwd);
		test=extent.createTest("Validation of user for order completion");
	
		
	    try
	    {
	    	add.addAllProds();
			Thread.sleep(2000);
			check.clickcheckOut();
	    	test.info(uname + " is on order page successfully");
		String checkOutCompletion_Msg= check.checkOutCompletion();
		Thread.sleep(2000);
		test.info(uname + " fetching the checkout message");
		String expectedCheckout_Msg="Checkout: Complete!";
		if(checkOutCompletion_Msg.equals(expectedCheckout_Msg) )
		{
			test.pass(uname + " : succesfully placed the order");
		Assert.assertTrue(true);	
		}
		test.info(uname + " trying to fetch the thanks message for their order");
		String orderCompletion_Msg = check.orderCompletion();
		String expected_OrderMsg="Thank you for your order!";
	      if (orderCompletion_Msg.equals(expected_OrderMsg))
            {
	    	  test.pass(uname + " : GOT A THANKS MESSAGE , SUCCESSFULLY ORDER HAS PLACED");
		Assert.assertTrue(true);
            }
	    }
	    catch(Exception e)
	    {
	    	Thread.sleep(2000);
	    	test.fail(uname + " couldn't reach the order checkout page");
	    	Assert.assertTrue(false);
	    }
	}
	
	

	
	
	@Test(dataProviderClass = ExcelRead.class, dataProvider = "fetchData", priority = 11)
	public void checkBackToHome(String user, String pwd)
	{
		
		log.do_login(user, pwd);
		test=extent.createTest(user +" : check whether the user able to return to product page");
		try
		{
			
		add.addAllProds();
		test.info(user + " : fetching the products");
		check.clickcheckOut();
		test.info(user + " : filling the informations to checkout");
		check.checkOutCompletion();
		test.info(user + " clicks on back to home button");
		
		
		String actualUrl = check.returnHome();
		Thread.sleep(2000);
		String expectedProdPageUrl = "https://www.saucedemo.com/inventory.html";
		
		if(actualUrl.equals(expectedProdPageUrl))
		{
			test.pass(user + " succesfully returned to product page i.e: " +actualUrl);
			Assert.assertTrue(true, "User product page url is not same");
		}
		else
		{
			test.fail(user + " couldn't return to product page");
			
		}
	
	}
		catch(Exception e)
		{
			test.fail(user + " couldn't reach checkout page, so no way to return back to the product page");
			Assert.assertTrue(false, " User handling the exception, couldnt reach the checkout page");
		}
		
	}
}

