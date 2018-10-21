package com.wallethub.project.utils;

import net.bytebuddy.dynamic.loading.ByteArrayClassLoader.ChildFirst;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wallethub.lib.utils.BaseClass;
import com.wallethub.lib.utils.Configuration;
import com.wallethub.lib.utils.DataHandlers;
import com.wallethub.lib.utils.Handlers;
import com.wallethub.lib.utils.TakingScreenshot;
import com.wallethub.ui.FacebookHomePage;
import com.wallethub.ui.FacebookLoginPage;

public class FacebookTestCase001 {

	WebDriver driver;

	DataHandlers dataHandler;
	FacebookLoginPage loginpage;
	FacebookHomePage homepage;
	BaseClass baseMethods;
	Handlers handler;
	String text = null;


	@BeforeMethod(description="This will save test case data in our property file")
	public void setup() {

		dataHandler.setDataToProperties("config", "browser", "chrome",
		"Setting chrome as value in property file to select chrome browser");
		
		dataHandler.setDataToProperties("config", "username", "Enter username",
		"Facebook account EmailAddress/Username");
		
		dataHandler.setDataToProperties("config", "password", "Enter password",
		"Facebook account password");
		
		dataHandler.setDataToProperties("config", "URL", 
		"https://www.facebook.com/login/", "Facebook URL");

		driver = Configuration.getDriverInstance();
		loginpage = new FacebookLoginPage(driver);
		homepage = new FacebookHomePage(driver);

	}

	@Test(description = "This testcase will perform facebook login, post "
			+ "a message on facebook and handle the alert if duplicate message comes.")
	public void testValidLoginAndPostMsg() throws InterruptedException {

		String username = DataHandlers.getDataFromProperties("config", "username");
		String password = DataHandlers.getDataFromProperties("config", "password");
    	loginpage.waitForFacebookPageToLoad();
		loginpage.getUsernameTextbox().sendKeys(username);
		loginpage.getPasswordTextbox().sendKeys(password);
		loginpage.getLoginButton().click();
		homepage.getHomeButton().click();
		homepage.getTextbox().click();
		baseMethods.HoverClickAndAddText(driver, homepage.writeText(), "Hello World!");
		homepage.clickPost().click();

		try {
			if ((homepage.duplicateMessagePopup()).isDisplayed()) {
				
				homepage.duplicateMessagePopup().click();
				System.out.println("You are trying to enter duplicate message which is not allowed in facebook");
						}
		   	}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		        }

		System.out.println("Please check alert message " + text);
		homepage.getLogoutDropdown().click();
		homepage.getLogoutButton().click();

		try {

			System.out.println("ALERT MESSAGE" + handler.getAlertMessageAndAccept(driver));

		    } catch (Exception e) {

			System.out.println(e.getMessage());
		          }
          }

	@AfterMethod(description="This will take screenshot if @Test fails")
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			System.out.println("Unable to perform login " + result
							+ "\n\nTo check error message please find Screenshot saved in Screenshot folder");

			//passing screenshot name
			TakingScreenshot.takeScreenShot(driver, "ErrorMessage.png");

		}

	}

	@AfterSuite
	public void closeDriverInstance() {
		driver.quit();
	}
}
