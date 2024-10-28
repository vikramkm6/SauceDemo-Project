package pages;

import org.openqa.selenium.By;

import driverFactory.BaseClass;

public class LoginPage extends BaseClass {

	
	//Locators for login page 
	By username= By.id("user-name");
	By password= By.id("password");
	By login_button =By.id("login-button");
	By error= By.xpath("//h3[@data-test='error']/text()");
	
	//Actions 
	
	public void do_login(String uname, String pwd)
	{
	com.enterValue(username,uname );   //calling the utilitiy method of sendkeys
	com.enterValue(password, pwd);
	com.doClick(login_button);        //calling the utility method of click
	}
	
  public String fetchText()
  {
	  String text = com.get_Text(error);
	  return text;
  }
	
	
	
	
	
}
