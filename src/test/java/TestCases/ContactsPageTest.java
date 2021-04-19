package TestCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import POM.ContactsPageHelper;
import POM.LoginPageHelper;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

import TestBase.TestBase;
import Utilities.ExcelReader;
import Utilities.Utilities;

public class ContactsPageTest extends TestBase {

	LoginPageHelper loginpagehelper;
	ContactsPageHelper contactspagehelper;
	int rowNum;

	@Test(priority = 1, dataProviderClass = Utilities.class, dataProvider = "dp")
	public void contactsPageTest(Hashtable<String, String> data) throws IOException, InterruptedException {

		String TCNO = data.get("TC No.");
		String URL = data.get("URL");
		String UserName = data.get("UserName");
		String Password = data.get("Password");
		String Firstname = data.get("First  Name");
		String Lastname = data.get("Last Name");
		rowNum = Integer.parseInt(data.get("rowNum"));

		System.out.println(TCNO);
		System.out.println(URL);
		System.out.println(Firstname);
		System.out.println(Lastname);

		// Putting Data in another Hash table so that we can use in helper/POM
		helper.put("userName", UserName);
		helper.put("password", Password);

		helper.put("FirstName", Firstname);
		helper.put("LastName", Lastname);

		System.out.println(helper);

		// Helper Class object
		loginpagehelper = new LoginPageHelper();
		contactspagehelper = new ContactsPageHelper();

		// Extent report
		test = report.startTest("loginPageTest[TC No :" + TCNO + "]");

		// Output excel object creation
		ExcelReader outputExcel = new ExcelReader(
				System.getProperty("user.dir") + "\\Outputs\\" + timeStamp + "\\Output_" + timeStamp + ".xlsx");

		// TestCase start
		invokeBrowser(URL);
		loginpagehelper.login();
		contactspagehelper.createConntacts();

	}

}
