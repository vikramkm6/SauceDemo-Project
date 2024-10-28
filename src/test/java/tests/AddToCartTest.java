package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import driverFactory.BaseClass;
import excelReader.ExcelRead;
import utilities.Screenshot;

public class AddToCartTest extends BaseClass {

	@Parameters({ "username", "password" })
	@Test(priority = 2)
	public void addToCart(String username, String password) throws InterruptedException {
		log.do_login(username, password);

		Thread.sleep(2000);
		test = extent.createTest("Adding items to the cart");
		add.adding_Products();

		Thread.sleep(5000);

		String actual = add.verifyAddToCart();
		if (actual.equals("3")) {
			Assert.assertTrue(true);
			test.pass("Items added to the cart and verified successfully for the user : " + username + " , equal to 3");
		} else {
			test.fail("Items didnt get match");
			test.addScreenCaptureFromPath(Screenshot.captureScreenshot());
		}
		Thread.sleep(5000);

		test = extent.createTest("Remove items from the cart");

		// Removing the cart items
		String removeCartItems = add.removeCartItems();

		if (removeCartItems.isEmpty()) {
			test.pass("Items removed (Cart Is empty) successfully for the user :  " + username);
			Assert.assertTrue(true);
		}

	}

	@Test(dataProviderClass = ExcelRead.class, dataProvider = "fetchData", priority = 3)
	public void sortProducts(String username, String password) {
		log.do_login(username, password);
		test = extent.createTest(username + " : Sorting the price validation from low to high");

		List<Double> originalPrices;

		try {
			add.clickDropdown("Price (low to high)");
			originalPrices = add.priceSorting();
			List<Double> sortedPrice = new ArrayList<>(originalPrices);
			Collections.sort(sortedPrice);

			if (originalPrices.equals(sortedPrice)) {

				test.pass(username + " Sorted items are matched");
				Assert.assertTrue(true);
			} else {

				test.fail(username + " Sorted items not matched");
				test.addScreenCaptureFromPath(Screenshot.captureScreenshot());
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			test.fail("Couldnt find the sorting option for " + username + ":" + " " + e.getMessage());
			test.addScreenCaptureFromPath(Screenshot.captureScreenshot());
		}

	}

	@Test(dataProviderClass = ExcelRead.class, dataProvider = "fetchData", priority = 4)
	public void click_Highest_Product(String username, String password) throws InterruptedException {
		log.do_login(username, password);
		Thread.sleep(5000);
		test = extent.createTest(username + " : Check click only with the highest product");
		Double maxPrice;
		List<Double> originalPrices;

		try {
			add.clickDropdown("Price (high to low)");
			test.info(username + " Selects the Filter :  Price (high to low) has selected");
			originalPrices = add.priceSorting();

			maxPrice = Collections.max(originalPrices);

			String max = String.valueOf(maxPrice);

			String actual_highestPrice = add.clickHighestProd_Price(max);
			test.info(username + " clicks the highest product i.e : " + max);
			if (actual_highestPrice.equals(max)) {

				test.pass(username + " " + actual_highestPrice + "and " + max + " are same");

				Assert.assertTrue(true);
			} else {
				test.fail("Prices are not same");

			}
		} catch (Exception e) {
			test.fail(username + " couldn't use filter Price (high to low)");
			
			Assert.assertTrue(false);
		}

	}

	@Test(dataProviderClass = ExcelRead.class, dataProvider = "fetchData", priority = 5)
	public void verifyProductDetails(String username, String password) {
		log.do_login(username, password);

		try {
			test = extent.createTest(" Verify product Details for different users");

			Thread.sleep(5000);
			int actualCount = add.fetchAllProductsCount();
			test.info(username + " : fetching all the products and its count");
			int expectedCount = 6;

			if (actualCount == expectedCount) {
				test.pass(username + " : " + actualCount + " products == " + expectedCount);
				Assert.assertTrue(true);
			} else {
				test.fail(username + " : " + actualCount + " products not equal to " + expectedCount);
			    Assert.assertTrue(false);
			}
		} catch (Exception e) {
			test.fail(username + " couldn't enter into the homepage");
		
			
		}
	}
	
	
	@Test(dataProviderClass = ExcelRead.class, dataProvider = "fetchData",priority=6)
	public void checkOut_Products_Validation(String username , String password)
	{
		log.do_login(username, password);
		test=extent.createTest(username  +" : validation for products checkout");
		try
		{
	  test.info(username + " : adding all the products to the cart");
	  int actualProds = add.addAllProds();
	  test.info(username + " : verifying the cart products");
	  int expectedProds = add.cartProducts();
	  
	  if(actualProds==expectedProds)
	  {
		  test.pass(username +" : validates the equal number of products in cart i.e " + actualProds+ " == " +expectedProds);
		  Assert.assertTrue(true);
	  }
	  else
	  {
		  test.fail(username +" : validates the equal number of products in cart but " + actualProds+ " is not equal to " +expectedProds);
		  Assert.assertTrue(false);
	  }
		}
		catch(Exception e)
		{
			test.fail(username + " : couldn't add the products");
	
		}
	  
	   
	  
	}
	
}
