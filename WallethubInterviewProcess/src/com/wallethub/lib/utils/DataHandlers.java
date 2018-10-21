package com.wallethub.lib.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

 

public class DataHandlers {

	// Generic functionality for setting data to properties file

	public static void setDataToProperties(String fileName, String key,
			String value, String comment)

	{
		try 
		{
			File f = new File("./Config/" + fileName + ".properties");
			FileInputStream fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);
			p.setProperty(key, value);
			FileOutputStream fos = new FileOutputStream(f);
			p.store(fos, comment);
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

 
	// getting data from properties file

	public static String getDataFromProperties(String fileName, String key)

	{
		String data = null;

		try

		{
			File f = new File("./Config/" + fileName + ".properties");
			FileInputStream fis = new FileInputStream(f);
			Properties p = new Properties();
			p.load(fis);
			data = (String) p.get(key);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return data;
	}
}
