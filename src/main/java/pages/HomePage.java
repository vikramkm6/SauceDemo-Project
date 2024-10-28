package pages;

import org.openqa.selenium.By;

import driverFactory.BaseClass;

public class HomePage extends BaseClass {
	
	
	//By locators for homepage 
	
	By menu_button= By.xpath("//button[@id='react-burger-menu-btn']");
	By logout= By.xpath("//a[text()='Logout']");
	
	
	public void click_logout()
	{
		
	
	com.doClick(menu_button);
	com.doClick(logout);
	
	}

}
