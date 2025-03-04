package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	
	private ElementUtil eleUtil;
	
	By firstName= By.id("input-firstname");
	By lastName= By.id("input-lastname");
	By email= By.id("input-email");
	By telephone= By.id("input-telephone");
	By password = By.id("input-password");
	By confirmpassword= By.id("input-confirm");
	By subscribeYes= By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1' ]");
	By subscribeNo= By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0' ]");
	By logoutLink= By.linkText("Logout");
	By registerLink= By.linkText("Register");
	By registerSuccessMsg= By.cssSelector("div#content h1");
	By agreeCheckbox= By.name("agree");
	By continueBtn= By.xpath("//input[@type='submit' and @value='Continue']");
	
	
	public RegisterPage(WebDriver driver) {
		
		this.driver=driver;
		
		eleUtil= new ElementUtil(driver);
		
	}
	
    public String userRegister(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
    	
    	eleUtil.doSendKeysWithVisibleElement(this.firstName, AppConstants.DEFAULT_TIME_OUT, firstName);
    	eleUtil.doSendKeys(this.lastName, lastName);
    	eleUtil.doSendKeys(this.email, email);
    	eleUtil.doSendKeys(this.telephone, telephone);
    	eleUtil.doSendKeys(this.password, password);
    	eleUtil.doSendKeys(this.confirmpassword, password);
    	
    	if (subscribe.equalsIgnoreCase("yes")) {
    		
    		eleUtil.doClick(subscribeYes);
    	}
    	
    	else {
    		
    		eleUtil.doClick(subscribeYes);
    	}
    	
    	eleUtil.doClick(this.agreeCheckbox);
    	eleUtil.doClick(this.continueBtn);
    	String succMesg= eleUtil.waitForElementVisible(registerSuccessMsg, AppConstants.DEFAULT_TIME_OUT).getText();
    	System.out.println("The success message is : " + succMesg);
    	
    	eleUtil.doClick(logoutLink);
    	eleUtil.doClick(registerLink);
    
    	return succMesg;
    	
    	
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
