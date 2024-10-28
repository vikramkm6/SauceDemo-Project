package driverFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AddToCart;
import pages.CheckOut;
import pages.HomePage;
import pages.LoginPage;
import utilities.Common;

public class BaseClass {

	public static WebDriver driver;
	public static Properties prop;

	public static AddToCart add = new AddToCart();
	public static CheckOut check = new CheckOut();
	public static Common com = new Common();
	public static LoginPage log = new LoginPage();
	public static HomePage home = new HomePage();
	public static ExtentReports extent;
	public static ExtentSparkReporter spark;
	public static ExtentTest test;

	@BeforeTest
	public void init() {

		ExtentSparkReporter spark = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\Report\\extentreport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);

	}

	@BeforeMethod
	public void setUp() throws IOException {

		// Create a new test instance for each test method

		if (driver == null) {
			File f = new File(
					System.getProperty("user.dir") + "\\src\\test\\resources\\configFiles\\config.properties");
			FileReader fr = new FileReader(f);
			prop = new Properties();
			prop.load(fr);
		}

		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else {
			System.out.println("Pass the valid browser ...");
		}

		driver.get(prop.getProperty("testUrl"));
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void tearDown() {

		driver.close();
		extent.flush();
	}

}
