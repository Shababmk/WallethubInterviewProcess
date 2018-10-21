package com.wallethub.lib.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseClass {
	
	
	//This method will provide today's date in desired format
	public static String todaysDate(String format){
		SimpleDateFormat sfd1 = new SimpleDateFormat(format);
		 Date date = new Date();
		 String todayDate = sfd1.format(date);
		 return todayDate;
	}
	//This method will hover over the element.
	public static void Hoverelement(WebDriver driver, WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.perform();
		
	}
	//This method will hover over the element and perform click.
	public static void HoverAndClick(WebDriver driver, WebElement element){
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.click();
		action.build().perform();
		
	}
	//This method will hover over the element and enter the given string into text box.
	public static void HoverClickAndAddText(WebDriver driver, WebElement element, String data){
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.click();
		action.sendKeys(data);
		action.build().perform();
		
		
	}
	
	   //This method will wait for a particular element to be visibles
		public static void WebDriverWait(WebDriver driver, WebElement element){
			org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOf(element));
			//element.click();
			
		}
		//This method will click on an element via jaavascriptexecutor
		public static void clickWithJavascriptExec(WebDriver driver, WebElement element){
			JavascriptExecutor js = (JavascriptExecutor)driver;	  
      	  js.executeScript("arguments[0].click();", element);
		}

		

         
		   //Creating the generic method for mouse hover via Javascript Executor
	public static void mouseHoverJScript(WebElement HoverElement,
			WebDriver driver) {
		try {
			if (isElementPresent(HoverElement)) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
						+ "evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} "
						+ "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript,
						HoverElement);

			} else {

				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {

			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {

			System.out.println("Element " + HoverElement
					+ " was not found in DOM" + e.getStackTrace());
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Error occurred while hovering"
					+ e.getStackTrace());
		}
	}

	// Creating generic method to identify whether element is present or not
	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;

		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}
	    		     


      
	

}
