package com.wallethub.lib.utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Handlers {

	//Accepting alert
	public static void acceptAlert(WebDriver driver) {

		Alert alt = driver.switchTo().alert();
		alt.accept();

	}
    
	//Dismiss the alert
	public static void dismissAlert(WebDriver driver) {

		Alert alt = driver.switchTo().alert();
		alt.dismiss();

	}

	//To verify whether alert available on webpage
	public boolean isDialogPresent(WebDriver driver) {
		boolean result;
		Alert alert = ExpectedConditions.alertIsPresent().apply(driver);
		if (alert != null) {
			result = true; // alert is there
		} else {
			result = false;
		}

		return result;
	}

	//Accept the alert and get alert message
	public static String getAlertMessageAndAccept(WebDriver driver) {

		Alert alert = driver.switchTo().alert();
		String alertmessage = alert.getText();
		alert.accept();
		return alertmessage;

	}

	//Send a text to alert and accept it
	public static void enterTextToAlert(WebDriver driver, String data) {

		Alert alt = driver.switchTo().alert();
		alt.sendKeys(data);
		alt.accept();

	}

	//getting parent window handle 
	public static String getParentWindowHandle(WebDriver driver) {

		return driver.getWindowHandle();
	}

	//getting child window handle
	public static String getWindowHandles(WebDriver driver, String parentHandle) {

		String realChildHandle = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> handler = handles.iterator();

		while (handler.hasNext()) {

			// System.out.println("Handles are"+handler.next());
			String cHandle = handler.next();
			if (!parentHandle.equals(cHandle)) {
				
				realChildHandle = cHandle;
				// System.out.println("childHandle "+childHandle );
			       }

		        }

       		return realChildHandle;
      	    }

	//switching to another window tab
	public static void windowSwitch(WebDriver driver, String handle) {

		driver.switchTo().window(handle);
     	}

}
