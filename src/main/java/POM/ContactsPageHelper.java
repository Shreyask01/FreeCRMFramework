package POM;

import java.io.IOException;

import TestBase.TestBase;

public class ContactsPageHelper extends TestBase {

	String actualResult = "";


	//this is object repository for contacts page
	
	public String createConntacts() throws IOException {
		object_Click("contacts_XPATH");
		object_Click("CreateContacts_XPATH");
		setText("Firstname_XPATH",helper.get("FirstName"));
		setText("Lastname_XPATH",helper.get("LastName"));
		object_Click("Social_XPATH");
		clickByText("Social_XPATH", helper.get("social"));
		object_Click("ContactsSave_XPATH");
		
		if(elementPresent("SuccessContacts_XPATH")) {
			actualResult = getTextFromElement("SuccessContacts_XPATH");
		}else if(elementPresent("BlankFirstName_XPATH")) {
			actualResult = getTextFromElement("BlankFirstName_XPATH");
		}else if(elementPresent("BlankLastName_XPATH")) {
			actualResult = getTextFromElement("BlankLastName_XPATH");
		}
		return actualResult;
	}
	
	//T
}