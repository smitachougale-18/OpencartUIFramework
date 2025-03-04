package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	
	public Properties prop;
	
	public static ThreadLocal <WebDriver> tlDriver= new ThreadLocal<WebDriver>();
	
	private static final Logger LOG= Logger.getLogger(DriverFactory.class);
			
	public OptionsManager optionsManager;
	
	/**
	 * This method initialize the driver on the basis of given browser name
	 * @param browserName
	 * @return It will return driver instance
	 */
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName =prop.getProperty("browser").toLowerCase();
		
		System.out.println("The given browser is : " + browserName);
		
		LOG.info("The given browser is : " + browserName);
		
		optionsManager = new OptionsManager(prop);
		
		
		
				
		if(browserName.equals("chrome")) {
		// driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if(browserName.equals("firefox")) {
		// driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver());
		}
		
		
		else if(browserName.equals("edge")) {
		//	 driver = new EdgeDriver();
			 tlDriver.set(new EdgeDriver());
			}
			
		else if(browserName.equals("safari")) {
		//	 driver = new SafariDriver();
			 tlDriver.set(new SafariDriver());
			}
		
		else {
			System.out.println("Please enter the right browser : " + browserName);
			
			LOG.error("Please enter the right browser : " + browserName);
			
			throw new FrameworkException(AppError.BROWSER_NOT_FOUND);
		}
		
	      getDriver().manage().deleteAllCookies();
		  getDriver().manage().window().maximize();
	      getDriver().get(prop.getProperty("url"));
		return getDriver();
		
	}
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
		
	}
	
	
	
	
	public Properties initProp() {
		
		/**
		 * This method is used to initialize config properties
		 */
		
	    prop = new Properties();
	    
	    FileInputStream ip=null;
	    
	    // mvn clean install -Denv="qa"
	    
	 //  String envName= System.getenv("env");
	   String envName= System.getProperty("env");
	   
	   System.out.println("----> Running test cases on environment : " +envName);
	   
	   if (envName==null) {
		   System.out.println("No environment is gievn...hence running it on QA envrionment");
		   
		   LOG.info("No environment is gievn...hence running it on QA envrionment");
		   
		   try {
			ip= new FileInputStream("./src/test/resource/Config/qa.config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   }
	    
	   else {
		 
		 try {  switch (envName) {
		   
		   case "qa":
			   ip= new FileInputStream("./src/test/resource/Config/qa.config.properties");
			   System.out.println("Env QA qa.config.properties");
			   break;
			   
		   case "stage":
			   ip= new FileInputStream("./src/test/resource/Config/stage.config.properties");
			   System.out.println("Env stage stage.config.properties");
			   break;   
			   
		   case "production":
			   ip= new FileInputStream("./src/test/resource/Config/config.properties");
			   System.out.println("Env prod config.config.properties");
			   break;   
			     
			default:
			System.out.println("Please enter the right environment : " +envName);
			
			throw new FrameworkException(AppError.ENV_NOT_FOUND);
			//break;
			   
			   
			   
		   
		   }
		
		 }
		 catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			 
		 }
		   
		   
		  }
	    
	    
	    
	    
	    
	      /* try {
			FileInputStream ip = new FileInputStream("./src/test/resource/Config/config.properties");
			
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	   
	   
	    try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return prop;
		
	}
	
	
	
	/**
	 * TakeScreenshot
	 */
	
	
	public static String getScreenshot() {
		
		File srcFile= ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir") + "/screenshot" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
	
	
	
	
	
	
	
	
}
