package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;

import TestBase.TestBase;

public class Utilities extends TestBase {

	public static void createExtentReport() {
		report = new ExtentReports(
				System.getProperty("user.dir") + "\\Outputs\\" + timeStamp + "\\Extent_" + timeStamp + ".html");
	}

	public static void flushExtentReport() {
		report.flush();
	}

	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir") + "\\Outputs\\" + timeStamp + "\\ScreenShots\\"
				+ System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object data[][] = new Object[rows - 1][1];

		Hashtable<String, String> table = null;
		int rowNum;
		
		for (rowNum = 2; rowNum <= rows; rowNum++) {
			
			table = new Hashtable<String, String>();
			String index = String.valueOf(rowNum);
			table.put("rowNum", index);
            //System.out.println(table);
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				table.put("RowNum", Integer.toString(rowNum));
				//System.out.println(table);
				data[rowNum - 2][0] = table;
				
			}
			
		}
		//System.out.println(data[0][0]);
		System.out.println(data[0][0]);
		return data;
		
	}
	
	
	
	
	
	
}



