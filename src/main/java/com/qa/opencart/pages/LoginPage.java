package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	
	private ElementUtil eleUtil;
	
	// By locator 
	
	private By emailId= By.name("email");
	private By password= By.name("password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By logoImage = By.cssSelector("img[title='naveenopencart']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink= By.linkText("Register");
	
	private static final Logger LOG= Logger.getLogger(LoginPage.class);
	
	
	// constructor
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	

	// Actions :
	
	
	public String getLoginPageTitle() {
		
		String title =eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE); 
		
//		String title =driver.getTitle();
		System.out.println("The login page title is :" +title);
		
		LOG.info("The login page title is :" +title);
		return title;
	}
	
	
	public boolean getLoginPageUrl() {
		
	String url=	eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		
//		String url =driver.getCurrentUrl();
		System.out.println("The Login page url is :" +url);
		
		LOG.info("The Login page url is :" +url);
		
		if (url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean isForgotPwdLinkExist() {
		
		return eleUtil.doEleIsDisplayed(forgotPwdLink);
		//return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User credentials are :" +username + ":" +pwd);
		
		LOG.info("User credentials are :" +username + ":" +pwd);
		
		eleUtil.doSendKeysWithWait(emailId, AppConstants.LARGE_TIME_OUT, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		
		//driver.findElement(emailId).sendKeys(username);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginBtn).click();
		
		return new AccountsPage(driver);
	}
	
	
	public RegisterPage navigateToRegisterPage() {
		
		System.out.println("Navigation to Register page......");
		LOG.info("Navigation to Register page......");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	
	
	
	
}
 