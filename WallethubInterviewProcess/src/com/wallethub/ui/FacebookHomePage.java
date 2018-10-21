package com.wallethub.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookHomePage {

	WebDriver driver;

	//Creating constructor to refer current class driver object
	public FacebookHomePage(WebDriver driver) {

		this.driver = driver;
	}

	//getting home button element
	public WebElement getHomeButton() {

		return driver.findElement(By.linkText("Home"));
	}

	//getting element of post message text box 
	public WebElement getTextbox() {

		return driver.findElement(By.xpath("//span[text() ='Compose Post']"));

	}

	//getting element of write post message textbox
	public WebElement writeText() {

		return driver.findElement(By
				.xpath("//div[@role='textbox']/div/div/div"));
	}

	//getting element of share post button
	public WebElement clickPost() {

		return driver
				.findElement(By
						.xpath("//button[@type='submit']/div/following-sibling::span[text() ='Share']"));

	}

	//getting close element of duplicate messagebox
	public WebElement duplicateMessagePopup() {

		return driver.findElement(By
				.xpath("//div[@class='_ohf rfloat']/div/a[text()='Close']"));
	}

	//getting dropdown element for logout button to be visible
	public WebElement getLogoutDropdown() {

		return driver.findElement(By.id("userNavigationLabel"));

	}

	//getting logout button element
	public WebElement getLogoutButton() {

		return driver.findElement(By.linkText("Log Out"));

	}

}
