package com.wallethub.lib.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

	 

	public class Configuration {

	 

	//Method created to use the appropriate browser available in properties file
	public static WebDriver getDriverInstance()
	{
	String browser = DataHandlers.getDataFromProperties("config", "browser");
	String url = DataHandlers.getDataFromProperties("config", "URL");

	WebDriver driver = null;

	//if key matches to firefox then launch firefox browser
	if(browser.equalsIgnoreCase("firefox")){
		
		FirefoxProfile ffprofile = new FirefoxProfile();
		ffprofile.setPreference("dom.webnotifications.enabled", false);
		
		
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(ffprofile);
	    System.setProperty("webdriver.firefox.marionette", "./Browser_Server/geckodriver.exe");
	    
	    
	    driver = new FirefoxDriver(options);

	}

	//if key matches to chrome then launch chrom browser
	else if(browser.equalsIgnoreCase("chrome"))
	{
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");			     

	    System.setProperty("webdriver.chrome.driver", "./Browser_Server/chromedriver.exe");
	    driver = new ChromeDriver(options);

	}
	else 
	{
	   System.err.println("-----Invalid Browser Option" + " Please check config.properties file available in folder Config");

	}
	   //Maximizing the browser
	   driver.manage().window().maximize();
	   //providing implicit wait
	   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	   //opening the url available in properties file
	   driver.get(url);

	   return driver;

	}
	
}
