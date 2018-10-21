package com.wallethub.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WallethubLoginPage {

	WebDriver driver;

	//Creating constructor to refer current class driver object
	public WallethubLoginPage(WebDriver driver) {

		this.driver = driver;
  	}

	//This method will wait for login page to load
	public void waitForWallethubPageToLoad() {

	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By
	.linkText("Login"))));
	}

	//getting login element 
	public WebElement clickOnLoginOption() {

		return driver.findElement(By.linkText("Login"));
	}
	
    //getting element of username textbox
	public WebElement getUsernameTextbox() {

		return driver.findElement(By.xpath("//div/input[@placeholder='Email Address']"));
	}

	 //getting element of password textbox
	public WebElement getPasswordTextbox() {
	 
	return driver.findElement(By.xpath("//div/input[@placeholder='Password']"));

	}
	
	//Getting login button element
		public WebElement getRememberMyEmailOption() {

			return driver.findElement(By.xpath("//div[@class='full left remember']/label//i[@class='track']"));
		}

	//Getting login button element
	public WebElement getLoginButton() {

		return driver.findElement(By.xpath("//div[@class='btns']/button/span[text()='Login']"));
	}

}
