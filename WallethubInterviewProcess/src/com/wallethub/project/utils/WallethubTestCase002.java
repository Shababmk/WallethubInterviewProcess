package com.wallethub.project.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ResourceBundle.Control;

import junit.framework.Assert;

import org.omg.CORBA.CTX_RESTRICT_SCOPE;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wallethub.lib.utils.BaseClass;
import com.wallethub.lib.utils.Configuration;
import com.wallethub.lib.utils.DataHandlers;
import com.wallethub.lib.utils.Handlers;
import com.wallethub.lib.utils.TakingScreenshot;
import com.wallethub.ui.WallethubInsuranceCmpnyPage;
import com.wallethub.ui.WallethubLoginPage;


public class WallethubTestCase002 {

	WebDriver driver;

	DataHandlers dataHandler;
	WallethubLoginPage loginpage;
	BaseClass baseMethods;
	Handlers handler;
	WallethubInsuranceCmpnyPage insCmpnyPage;
	String text = null;
	String actualColor;
	String[] hexValue;
	String review =null;

	@BeforeMethod(description="This will save test case data in our property file")
	public void setup() {

		dataHandler.setDataToProperties("config", "browser", "chrome",
		"Setting chrome as value in property file to select chrome browser");
		
		dataHandler.setDataToProperties("config", "username", "Enter username",
		"Wallethub account EmailAddress/Username");
		
		dataHandler.setDataToProperties("config", "password", "Enter password",
		"Facebook account password");
		
		dataHandler.setDataToProperties("config", "URL", 
		"https://wallethub.com/profile/test_insurance_company/", "Wallethub URL");

		driver = Configuration.getDriverInstance();
		loginpage = new WallethubLoginPage(driver);
		insCmpnyPage = new WallethubInsuranceCmpnyPage(driver);
		baseMethods = new BaseClass();

	}

	@Test(description = "This testcase will perform Wallethub login, lighiting stars verification, post a review and verify that message.")
	public void testLogin() throws Exception {

		String username = DataHandlers.getDataFromProperties("config", "username");
		String password = DataHandlers.getDataFromProperties("config", "password");
		
    	loginpage.waitForWallethubPageToLoad();
    	loginpage.clickOnLoginOption().click();
		loginpage.getUsernameTextbox().sendKeys(username);
		loginpage.getPasswordTextbox().sendKeys(password);
		baseMethods.HoverAndClick(driver, loginpage.getRememberMyEmailOption());
		loginpage.getLoginButton().click();
		baseMethods.Hoverelement(driver, insCmpnyPage.rating());
		
    	baseMethods.Hoverelement(driver, insCmpnyPage.forthStar(4));
    	
        Assert.assertTrue(insCmpnyPage.isStarsSelectedOnMouseHover(4, driver));
        TakingScreenshot.takeScreenShotOfElement(driver, "lightingStars.png", insCmpnyPage.ledLitScreenshot());
        
        
       // baseMethods.WebDriverWait(driver, insCmpnyPage.notification());
        
        try {
        	
			if ((insCmpnyPage.notification()).isDisplayed()) {
				baseMethods.clickWithJavascriptExec(driver, insCmpnyPage.notification());
				
					}
		   	}
		catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		        }

        
        baseMethods.HoverAndClick(driver, insCmpnyPage.forthStar(4));
        driver.navigate().back();
        baseMethods.Hoverelement(driver, insCmpnyPage.rating());
        baseMethods.HoverAndClick(driver, insCmpnyPage.forthStar(5));
        insCmpnyPage.selectYourPolicy().click();
        insCmpnyPage.healthOption().click();
        
		review = "I am writing review about Star Health Insurance Customer "
				+ "Care acts very fast and they process your documents with in 24 hrs of the call. "
				+ "Their representative will visit you at hospital, will take complete details of "
				+ "your medical problem.One of my colleague's claim settlement is almost 70% to 90% "
				+ "and received payment by cheque with in 15-35 days max from the date of claim "
				+ "application.There are various range of plans right from individual to a family"
				+ " and now Star Health has included aged family member also with easy term and "
				+ "condition.Premium is also equivalent in comparison to other family plans. "
				+ "No extra charge or hidden charges.Till now which ever staff I met are very "
				+ "friendly and helped me for preparing proper documents to settle claim at "
				+ "the earliest.Excellent service, keep it up.";

		  baseMethods.clickWithJavascriptExec(driver, insCmpnyPage.writeReview());
		  Thread.sleep(3000);
		  Robot robot = new Robot();
	      robot.setAutoDelay(40);
	      robot.setAutoWaitForIdle(true);
	      
	      robot.keyPress(KeyEvent.VK_CONTROL);
	      robot.keyPress(KeyEvent.VK_A);
	      robot.delay(500);
	      robot.keyPress(KeyEvent.VK_DELETE);
	      robot.delay(500);
	           
	      robot.keyRelease(KeyEvent.VK_CONTROL);
	      robot.keyRelease(KeyEvent.VK_A);
	      robot.keyRelease(KeyEvent.VK_DELETE);
	      robot.delay(100);
	     
	      
		//insCmpnyPage.writeReview().sendKeys(Keys.CONTROL, "A");
		insCmpnyPage.writeReview().sendKeys(review);
		baseMethods.clickWithJavascriptExec(driver, insCmpnyPage.overAllRating(5));
        
        insCmpnyPage.reviewSubmitButton().click();
        baseMethods.Hoverelement(driver, insCmpnyPage.getelementToMouseHoverForLogoutAndProfileButton());
        insCmpnyPage.profileOption().click();
        

        //Verifying that today's post available
		if ((insCmpnyPage.getDateElementToVerifyPost()).getText().contains(
				baseMethods.todaysDate("MMM dd, YYYY"))) {

			text = insCmpnyPage.getElementTextToVerifyPost().getText();
			text = text.substring(0, text.indexOf("… read more"));
			if (review.contains(text)) {
				System.out.println("Today's post available ");
			} else {
				System.out.println("You have not posted anything yet");
			}

		}

		baseMethods.Hoverelement(driver, insCmpnyPage.getelementToMouseHoverForLogoutAndProfileButton());
		insCmpnyPage.logoutButton().click();
		//To verify logout
		baseMethods.WebDriverWait(driver, loginpage.clickOnLoginOption());
	}
        
	@AfterMethod(description = "This will take screenshot if @Test fails")
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			System.out.println("Unable to perform login "+ result+ "\n\nTo check error message please "
					+ "find Screenshot saved in Screenshot folder");
			// passing screenshot name
			TakingScreenshot.takeScreenShot(driver, "WallethubErrorMessage.png");

		}
    	}

    	@AfterSuite
    	public void closeDriverInstance() {
    		driver.quit();
    	}

}