package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {

	private Properties prop;
	
	private ChromeOptions co;
	
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
}
