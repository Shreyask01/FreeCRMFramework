package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.*;

import Utilities.ExcelReader;
import Utilities.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static FileInputStream fs;
	public static Properties pro;
	public static Properties OR;
	public static ExtentTest test;
	public static ExtentReports report;
	public static Hashtable<String, String> helper = new Hashtable<String, String>();
	public static Hashtable<String, String> helper1 = new Hashtable<String, String>();
	public static ExcelReader excel;


	public static String timeStamp = new SimpleDateFormat("ddMMyyHHmmss").format(Calendar.getInstance().getTime());
  
	static {
		SimpleDateFormat dateformat = new SimpleDateFormat("ddMMyyyy");
		System.setProperty("FolderLocation", timeStamp);
		System.setProperty("CurrentDate", dateformat.format(new Date()));
	}

	private static final Logger logger = LogManager.getLogger(TestBase.class);


	@BeforeSuite
	public void setUp() throws IOException {
		loadPropertyFiles();
		copyOutputFile();
		Utilities.createExtentReport();
	}

	public static void loadPropertyFiles() throws IOException {
		
		fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resource\\config.properties");
		pro = new Properties();
		pro.load(fs);

		fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resource\\OR.properties");
		OR = new Properties();
		OR.load(fs);
	    excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\main\\java\\InputData\\"+pro.getProperty("ExcelsheetName")+".xlsx");
	   
	}

	public static void copyOutputFile() throws IOException {
		File src = new File(System.getProperty("user.dir") + "\\src\\main\\java\\InputData\\"+pro.getProperty("ExcelsheetName")+".xlsx");
		File dest = new File(
				System.getProperty("user.dir") + "\\Outputs\\" + timeStamp + "\\Output_" + timeStamp + ".xlsx");
		FileUtils.copyFile(src, dest);
	}

	public static void invokeBrowser() throws IOException {
		String URL = pro.getProperty("URL");
		String BrowserName = pro.getProperty("Browser");

		if (BrowserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("Firefox")) {
	
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (BrowserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

		driver.get(URL);
		test.log(LogStatus.PASS, test.addScreenCapture(Utilities.capture(driver)));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void closeBrowser() {
		driver.quit();
	}

	
	public static void object_Click(String locator) throws IOException {

		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CLASS")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
		}

	}
	
	
	public static void clickByText(String locator, String text) {
		
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath("//*[text()='"+text+"']")).click();
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CLASS")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
		}	
	}

	public static void setText(String locator, String value) throws IOException {

		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CLASS")) {
			driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
		}

	}

	public static boolean elementPresent(String locator) {

		int count = 0;

		if (locator.endsWith("_XPATH")) {
			count = driver.findElements(By.xpath(OR.getProperty(locator))).size();
		} else if (locator.endsWith("_CSS")) {
			count = driver.findElements(By.cssSelector(OR.getProperty(locator))).size();
		} else if (locator.endsWith("_NAME")) {
			count = driver.findElements(By.name(OR.getProperty(locator))).size();
		} else if (locator.endsWith("_CLASS")) {
			count = driver.findElements(By.className(OR.getProperty(locator))).size();
		}
		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	public static String getTextFromElement(String locator) {
		String elementText ="";
		
		if (locator.endsWith("_XPATH")) {
			elementText = driver.findElement(By.xpath(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_CSS")) {
			elementText = driver.findElement(By.cssSelector(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_NAME")) {
			elementText = driver.findElement(By.name(OR.getProperty(locator))).getText();
		} else if (locator.endsWith("_CLASS")) {
			elementText = driver.findElement(By.className(OR.getProperty(locator))).getText();
		}
		return elementText;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
