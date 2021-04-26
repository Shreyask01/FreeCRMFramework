package TestCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import POM.CalendarPageHelper;
import POM.LoginPageHelper;
import TestBase.TestBase;
import Utilities.ExcelReader;
import Utilities.Utilities;

public class CalendarPageTest extends TestBase {

	LoginPageHelper loginpagehelper;
	CalendarPageHelper calendarpagehelper;
	String result = "";
	int rowNum;

	@Test(priority = 1, dataProviderClass = Utilities.class, dataProvider = "dp")
	public void calenderPageTest(Hashtable<String, String> data) throws IOException {
		try {
		String TC = data.get("TC No.");
		String title = data.get("Title");
		String calendar = data.get("Calendar");
		String Category = data.get("Category");
		String StartDate = data.get("Start Date");
		String EndDate = data.get("End Date");
        String expectedResult = data.get("Expected Result");  
        			
		rowNum = Integer.parseInt(data.get("rowNum"));

		// Putting Data in another Hash table so that we can use in helper/POM

		helper.put("Title", title);
		helper.put("Calendar", calendar);
		helper.put("Category", Category);
		helper.put("startDate", StartDate);
		helper.put("endDate", EndDate);

		// Create Objects of Class
		loginpagehelper = new LoginPageHelper();
		calendarpagehelper = new CalendarPageHelper();

		// Extent report
		test = report.startTest("CalendarPageTest[TC No :" + TC + "]");

		// Output excel object creation
		ExcelReader outputExcel = new ExcelReader(
				System.getProperty("user.dir") + "\\Outputs\\" + timeStamp + "\\Output_" + timeStamp + ".xlsx");
		
		invokeBrowser();
		loginpagehelper.login();
		result = calendarpagehelper.createCalendar();
		if (result.equals(expectedResult)) {
		 	outputExcel.setCellData("CalendarPageTest", "Actual Result", rowNum, result);
			outputExcel.setCellData("CalendarPageTest", "Result", rowNum, "PASS");
			test.log(LogStatus.PASS, "CalendarPageTest successful");
		} else {
			outputExcel.setCellData("CalendarPageTest", "Actual Result", rowNum, result);
			outputExcel.setCellData("CalendarPageTest", "Result", rowNum, "FAIL");
			test.log(LogStatus.FAIL,
					test.addScreenCapture(Utilities.capture(driver)) + "CalendarPageTest Unsucessful");
		}

	} catch (Exception e) {
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.capture(driver)) + e.getStackTrace());
	} finally {
		Utilities.flushExtentReport();
		//closeBrowser();
	}


	}

}

