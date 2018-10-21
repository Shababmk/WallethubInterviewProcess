package com.wallethub.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 

public class FacebookLoginPage {

	WebDriver driver;

	//Creating constructor to refer current class driver object
	public FacebookLoginPage(WebDriver driver) {

		this.driver = driver;
  	}

	//This method will wait for login page to load
	public void waitForFacebookPageToLoad() {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
	.xpath("//div[@id ='header_block']/span[text()='Log in to Facebook']"))));
	}

    //getting element of username textbox
	public WebElement getUsernameTextbox() {

		return driver.findElement(By.id("email"));
	}

	 //getting element of password textbox
	public WebElement getPasswordTextbox() {
	 
	return driver.findElement(By.id("pass"));

	}

	//Getting login button element
	public WebElement getLoginButton() {

		return driver.findElement(By.id("loginbutton"));
	}

}
