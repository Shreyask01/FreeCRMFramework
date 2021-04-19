package POM;

import java.io.IOException;

import TestBase.TestBase;

public class ContactsPageHelper extends TestBase {

	String actualResult = "";


	//this is object repository for contacts page
	
	public void createConntacts() throws IOException {
		object_Click("contacts_XPATH");
		object_Click("CreateContacts_XPATH");
		setText("Firstname_XPATH",helper.get("FirstName"));
		setText("Lastname_XPATH",helper.get("LastName"));
		object_Click("ContactsSave_XPATH");
		
	}
	
	//T
}