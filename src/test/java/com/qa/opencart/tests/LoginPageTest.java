package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test (priority =1)
	public void loginPageTitleTest() {
	
		String actualTitle =loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test (priority =2)
	
	public void loginPageUrlTest() {
		
	Assert.assertTrue(loginPage.getLoginPageUrl());
	
	}
	
	@Test (priority=3)
	
	public void isForgotPwdLinkExist() {
    
		Assert.assertEquals(loginPage.isForgotPwdLinkExist(), true);
	}
	
	@Test (priority=4)
	
	public void doLoginTest(){
	accPage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	
	
	
	
	
	
	
}
