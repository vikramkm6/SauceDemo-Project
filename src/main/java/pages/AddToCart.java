package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import driverFactory.BaseClass;

public class AddToCart  extends BaseClass{
	
	//Locators 
	
	By container= By.id("//span[@class='select_container']");
	By dropdown = By.xpath("//select[@class='product_sort_container']");
	By prod= By.xpath("//button[text()='Add to cart']");
	By verify_addToCart= By.xpath("//a[@class='shopping_cart_link']/span");
	By remove= By.xpath("//button[text()='Remove']");
	By empty_cart= By.xpath("//div[@id='shopping_cart_container']/a");
	
	//By prices=By.xpath("//div[@class='inventory_item_price']/text()[2]");
	By prices = By.xpath("//div[@class='inventory_item_price']");
	By products = By.xpath("//div[@class='inventory_item_name']");
	By product_itemsCart= By.xpath("//div[@class='cart_item']");
	
   // By highest_price= By.xpath("//div[@class='inventory_item_price']/text()[2]");
	
	// //div[text()='7.99']/following-sibling::button
	
	//Actions
	public void clickDropdown(String text)
	{
		WebElement dropdown_Element = driver.findElement(dropdown);
		com.selectDropdown(dropdown_Element,text);
	}
	public void adding_Products()
	{
		List<WebElement> products = driver.findElements(prod);
		for(int i=0; i<=2;i++)
		{
			products.get(i).click();
		}
	}
	public String verifyAddToCart()
	{
		String text = driver.findElement(verify_addToCart).getText();
		return text;
	}

	public String removeCartItems()
	{
	List<WebElement> remove_Elements = driver.findElements(remove);
	for(WebElement r: remove_Elements)
	{
		r.click();
	}
	String text = driver.findElement(empty_cart).getText();
	return text;
	}
	
	public List<Double> priceSorting()
	{
		List<Double> list = new ArrayList<>();
		
		List<WebElement> elements = driver.findElements(prices);
		
		for(WebElement ele: elements)
		{
			String text = ele.getText();
			
			String replace = text.replace("$", "");
			
			double price = Double.parseDouble(replace);
			
			list.add(price);
		
			
		}
		return list;
	}
	
	public String clickHighestProd_Price(String price)
	{
		String beforeXpath ="//div[text()='";
		String afterXpath ="']/following-sibling::button";
		String actualXpath=beforeXpath + price+afterXpath;
		
	   By actual = By.xpath(actualXpath);
	   com.doClick(actual);
	   
	   com.doClick(verify_addToCart);
	   
	   String get_Text = com.get_Text(prices);
	   String highest_price = get_Text.replace("$", "");
	   return highest_price;
	   
	}
	
	public int fetchAllProductsCount()
	{
		List<WebElement> elements = driver.findElements(products);
		
		int size = elements.size();
		
		return size;
	}
	
	public int  addAllProds()
	{
		List<WebElement> products= driver.findElements(prod);
		
		for(WebElement prods: products)
		{
			prods.click();
		}
		
		com.doClick(verify_addToCart);
		int addToCart_no = products.size();
		return addToCart_no;
	}
	
	
	public int cartProducts()
	{
		List<WebElement> allItems = driver.findElements(product_itemsCart);
		int cartProds_number = allItems.size();
		return  cartProds_number;
	}
	
	
}
