package TestCases;


import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import POM.LoginPageHelper;
import TestBase.TestBase;
import Utilities.ExcelReader;
import Utilities.Utilities;

public class LoginPageTest extends TestBase {

	LoginPageHelper loginpagehelper;
	String result = "";
	int rowNum;

	@Test(priority = 1, dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginPageTest(Hashtable<String, String> data) throws IOException {
		try {
			// Getting data from Excel
			String TCNO = data.get("TC No.");
			String URL = data.get("URL");
			String userName = data.get("UserName");
			String password = data.get("Password");
			String expectedResult = data.get("Expected Result");
			rowNum = Integer.parseInt(data.get("rowNum"));

			// Putting Data in another Hash table so that we can use in helper/POM classes
			helper.put("userName", userName);
			helper.put("password", password);
			
			helper.put("expectedResult", expectedResult);

			// Extent report
			test = report.startTest("loginPageTest[TC No :" + TCNO + "]");

			// Output excel object creation
			ExcelReader outputExcel = new ExcelReader(
					System.getProperty("user.dir") + "\\Outputs\\" + timeStamp + "\\Output_" + timeStamp + ".xlsx");

			// Helper Class object
			loginpagehelper = new LoginPageHelper();

			// TestCase start 	
			invokeBrowser();
			result = loginpagehelper.login();
			if (result.equals(expectedResult)) {
				outputExcel.setCellData("loginPageTest", "Actual Result", rowNum, result);
				outputExcel.setCellData("loginPageTest", "Result", rowNum, "PASS");
				test.log(LogStatus.PASS, "LoginTest testcase successful");
			} else {
				outputExcel.setCellData("loginPageTest", "Actual Result", rowNum, result);
				outputExcel.setCellData("loginPageTest", "Result", rowNum, "FAIL");
				test.log(LogStatus.FAIL,
						test.addScreenCapture(Utilities.capture(driver)) + "LoginTest testcase Unsucessful");
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.capture(driver)) + e.getStackTrace());
		} finally {
			Utilities.flushExtentReport();
			//closeBrowser();
		}

	}

}

