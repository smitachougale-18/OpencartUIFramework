package com.qa.opencart.tests;



import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;
//import java.util.Random;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	
	public void registerSetup() {
		
		registerPage=loginPage.navigateToRegisterPage();
		
		}
	
	
	@DataProvider
	
	public Object[][] getRegTestData() {
		
		Object regData[][]= ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	
	
	public String getRandomEmail() {
		
		Random random= new Random();
		String email= "automationtest"+random.nextInt(1000)+"@gmail.com";
		return email;
		
	}
	
	@Test (dataProvider="getRegTestData" )
	public void registerUserTest(String firstName, String lastName, String email, String telephone, String password, String subscribe)
	{
		
		String actSuccMesg=registerPage.userRegister(firstName,lastName,getRandomEmail(),telephone, password,subscribe);
		
		Assert.assertEquals(actSuccMesg, AppConstants.ACC_CREATE_SUCC_MESG);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
