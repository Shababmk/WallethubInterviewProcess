package com.wallethub.lib.utils;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class TakingScreenshot {

	WebDriver driver;

	//getting file seprator 
	private static String fileSeperator = System.getProperty("file.separator");

	//This method will take screenshot of a webpage and name that screenshot as given name
	public static String takeScreenShot(WebDriver driver, String screenShotName) {

		try {

			File file = new File("./Screenshot");     
			if (!file.exists()) {
			System.out.println("File created " + file);
			file.mkdir();
			}

			File screenshotFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			File targetFile = new File("./Screenshot" + fileSeperator
					+ screenShotName);
			FileHandler.copy(screenshotFile, targetFile);
			return screenShotName;

		 } 
		catch (Exception e) {

			System.out.println("An exception occured while taking screenshot "
					+ e.getMessage());

			return null;

		}

	}

	//This method will take screenshot of a webpage and create a red color box around the element we will pass 
	public static String takeScreenShotOfElement(WebDriver driver,
			String screenShotName, WebElement element) {

		try {

			File file = new File("./Screenshot");
			if (!file.exists()) {
			System.out.println("File created " + file);
			file.mkdir();

			}

			((JavascriptExecutor) driver).executeScript(
					"arguments[0].style.border='3px solid red'", element);
			File screenshotFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			File targetFile = new File("./Screenshot" + fileSeperator
					+ screenShotName);
			FileHandler.copy(screenshotFile, targetFile);
			return screenShotName;

		 } 
		catch (Exception e) {

		System.out.println("An exception occured while taking screenshot "
					+ e.getMessage());

		return null;
		}

	}
}
