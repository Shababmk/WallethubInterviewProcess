package com.wallethub.ui;

import java.util.List;

import javax.lang.model.util.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WallethubInsuranceCmpnyPage {
	
	WebDriver driver;

	//Creating constructor to refer current class driver object
	public WallethubInsuranceCmpnyPage(WebDriver driver) {

		this.driver = driver;
  	}
	//This method will return notification element which appears on insurance page
	public WebElement notification(){
		return driver.findElement(By.xpath("//span[@class='cta_arrow down']/i[@class='af-icon-cross']"));
	}
	//This method will return element for hover to bring appearance of stars
	public WebElement rating(){
		return driver.findElement(By.xpath("//div[@class='reviewinfo info']/span[contains(text(), 'Rating')]"));
	}
	//This method will return nth star element 
	public WebElement forthStar(int starToSelect){
		return driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[text()='"+starToSelect+"']"));
	}
	//This method will return element to verify stars lighiting up or not
	public boolean isStarsSelectedOnMouseHover(int n, WebDriver driver){
	    Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[text()= '"+n+"']"))).perform();

	
		List<WebElement> stars = driver.findElements(By.xpath("//div[@class='wh-rating-choices-holder']/a"));
		
		for(int i=1; i<=4; i++){
			if(!stars.get(i).getAttribute("class").contains("hover"))
			{
				return true;
			}
		}
	    return false;
	}
	//this method will return element to take screenshot of lighiting element
	public WebElement ledLitScreenshot(){
		return driver.findElement(By.xpath("//div[@class='wh-rating-choices-holder']"));
	}
   //This method will return policy element 
	public WebElement selectYourPolicy(){
		return driver.findElement(By.xpath("//div[@class='dropdown-title']/span[text()='Please select your policy']"));
	}
    //This method will return health element
	public WebElement healthOption(){
		return driver.findElement(By.xpath("//div[@class='dropdown-title']//following-sibling::*/li/a[text()='Health']"));
		
	}
	//This method will return review message box element
	public WebElement writeReview(){
		return driver.findElement(By.id("review-content"));
	}
	//This method will return overall rating star element
	public WebElement overAllRating(int overAllRating){
		return driver.findElement(By.xpath("//span[@id='overallRating']/a/span[text()='"+overAllRating+"']"));
	}
	//This method will return submit button element to submit written review message
	public WebElement reviewSubmitButton(){
		return driver.findElement(By.xpath("//div[@class='submit']/input[@type='submit']"));
	}
	//This method will return element to hover and bring profile and logout button visible
	public WebElement getelementToMouseHoverForLogoutAndProfileButton(){
		return driver.findElement(By.xpath("//div[@class='burger-menu menu-loggedIn']/nav[@id= 'burger-more']//following-sibling::a"));
	}
	//This method will return profile element
	public WebElement profileOption(){
		return driver.findElement(By.linkText("Profile"));
	}
	//This method will return review text element to get text message for verification
	public WebElement getElementTextToVerifyPost(){
		 
		List<WebElement> text= driver.findElements(By.xpath("//*[contains(@id,'activity-content')]//following-sibling::p[@class='feeddesc']"));
		 return text.get(0);
	}
	//This method will return date element to verify whether it is today's post
	public WebElement getDateElementToVerifyPost(){
		List<WebElement> dates = driver.findElements(By.xpath("//*[contains(@id,'activity-content')]//following-sibling::span[@class='date']"));
		return dates.get(0);
	}
	//This method will return logout button element
	public WebElement logoutButton(){
		return driver.findElement(By.linkText("Logout"));
	}
	
	
}
