package POM;

import java.io.IOException;

import TestBase.TestBase;

public class LoginPageHelper extends TestBase {

	String actualResult = "";

	//this is loginpage
	
	public String login() throws IOException {
		setText("emailField_NAME", helper.get("userName"));
		setText("passwordField_NAME", helper.get("password"));
		object_Click("loginbtn_XPATH");
		if (elementPresent("errorMsg_XPATH")) {
			actualResult = getTextFromElement("errorMsg_XPATH");
		} else if (elementPresent("successMsg_CLASS")) {
			actualResult = getTextFromElement("successMsg_CLASS");
		}
		return actualResult;
	}

}
