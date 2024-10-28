package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.BaseClass;

public class Common extends BaseClass {
	
	
	public void enterValue(By loc, String text)
	{
	 
		driver.findElement(loc).sendKeys(text);
	}

	
	public void doClick(By loc)
	{
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(loc)).click();
	}
	
	public String fetchTitle()
	{
		String title = driver.getTitle();
		return title;
	}
	
	public String fetchUrl()
	{
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	
	public String get_Text(By loc)
	{
		
		String text = driver.findElement(loc).getText();
		return text;
	}
	
	
	public void selectDropdown(WebElement element, String text)
	{
		Select s = new Select (element);
		s.selectByVisibleText(text);
		
	}

	
	

}
