package pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import driverFactory.BaseClass;

public class CheckOut extends BaseClass {

	By checkOut = By.xpath("//button[@id='checkout']");
	By firstname = By.id("first-name");
	By lastname = By.id("last-name");
	By postalCode = By.id("postal-code");
	By continue_button = By.id("continue");
	By chk_Overview = By.xpath("//span[text()='Checkout: Overview']");
	By products = By.xpath("//div[@class='inventory_item_price']");
	By summary_total = By.xpath("//div[@class='summary_info_label summary_total_label']");
	By tax = By.xpath("//div[@class='summary_tax_label']");
	By finish = By.xpath("//button[@name='finish']");
	By checkout_comp = By.xpath("//span[text()='Checkout: Complete!']");
	By orderMsg = By.xpath("//h2[text()='Thank you for your order!']");
	By backHome = By.xpath("//button[@id='back-to-products']");
	By errorMsg= By.xpath("//h3[@data-test='error']");
	// Actions

	public String clickcheckOut() {
		com.doClick(checkOut);
		com.enterValue(firstname, "vikram");
		com.enterValue(lastname, "kumar");
		com.enterValue(postalCode, "600078");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		com.doClick(continue_button);
		String get_Text = com.get_Text(chk_Overview);
		return get_Text;
	}
	
	public String leavingMandatoryFields()
	{
		com.doClick(checkOut);
		com.doClick(continue_button);
		String text = com.get_Text(errorMsg);
		return text;
	}

	public double getTotal() {
		String total = com.get_Text(summary_total);

		// System.out.println(total);
		Pattern pattern = Pattern.compile("\\d+\\.\\d+"); // Matches any number with decimal places.
		Matcher matcher = pattern.matcher(total);

		// Check if a match is found.
		if (matcher.find()) {
			// Extract and parse the matched double value.
			String doubleValueStr = matcher.group();
			return Double.parseDouble(doubleValueStr);
		}
		return 0;
	}

	public double getTotalProducts() {

		double total = 0;

		String taxPrice = com.get_Text(tax);

		// Tax: $10.40
		String finalTaxPrice = taxPrice.substring(6);
		double finalTaxDoublePrice = Double.parseDouble(finalTaxPrice);
		List<WebElement> elements = driver.findElements(products);
		for (WebElement ele : elements) {
			String price = ele.getText().replace("$", "");
			double actualPrice = Double.parseDouble(price);
			total += actualPrice;
		}

		double targetPrice = total + finalTaxDoublePrice;
		System.out.println("Consolidated value :" + targetPrice);
		return targetPrice;
	}

	public String checkOutCompletion() {
		com.doClick(finish);
		String text = com.get_Text(checkout_comp);
		return text;

	}

	public String orderCompletion() {
		String order_Msg = com.get_Text(orderMsg);
		return order_Msg;
	}
	
	public String returnHome()
	{
		com.doClick(backHome);
		String fetchUrl = com.fetchUrl();
		return fetchUrl;
		
	}
}
