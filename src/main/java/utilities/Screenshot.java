package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import driverFactory.BaseClass;

public class Screenshot extends BaseClass  {
	
	
	public static String captureScreenshot()
	{
		TakesScreenshot tks = (TakesScreenshot)driver;
		File temp = tks.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("./screenshotfailed/Sauce" +System.currentTimeMillis()+".png");
		try {
			FileUtils.copyFile(temp, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String absolutePath = dest.getAbsolutePath();
		return absolutePath;
	}
	}


