package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	
	private ChromeOptions co;
	
	private FirefoxOptions fo;
	
	
	public OptionsManager(Properties prop) {
		
		this.prop=prop;
	}
	
	public ChromeOptions getChromeOptions() {
		
		co= new ChromeOptions();
		System.out.println("Headless "+prop.getProperty("headless"));
		System.out.println(Boolean.parseBoolean(prop.getProperty("headless")));
		System.out.println(prop.getProperty("incognito"));
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
			
			
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
			
			
		}
		
		return co;
		
	}
	
	
public FirefoxOptions getFirefoxOptions() {
		
		fo= new FirefoxOptions();
		System.out.println("Headless "+prop.getProperty("headless"));
		System.out.println(Boolean.parseBoolean(prop.getProperty("headless")));
		System.out.println(prop.getProperty("incognito"));
		if(Boolean.parseBoolean(prop.getProperty("headless")))
		{
			co.addArguments("--headless");
			
			
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito")))
		{
			co.addArguments("--incognito");
			
			
		}
		
		return fo;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
