package POM;

import java.io.IOException;

import TestBase.TestBase;

public class CalendarPageHelper extends TestBase{

	String actualResult = "";
	
	public String createCalendar () throws IOException {
		
		object_Click("calender_XPATH");
		object_Click("createCalender_XPATH");
		setText("Title_NAME","Title");
		object_Click("CalenderDrop_NAME");
		object_Click("NewCalendar_XPATH");
		setText("AddCalendarName_NAME","Calendar");
		object_Click("SaveCalendar_XPATH");
		object_Click("Category_NAME");
		
		clickByText("CategoryDrop_XPATH", helper.get("Category"));
		
		
		setText("StartDate_CLASS","");
		setText("EndDate_XPATH","");
		object_Click("CalendarSave_XPATH");
		
	
		if(elementPresent("CalendarSuccess_XPATH")) {
			actualResult = getTextFromElement("CalendarSuccess_XPATH");
		}else  {
			System.out.println("There is an validation error");
		}
		return actualResult;
		
	}
	
	
}
