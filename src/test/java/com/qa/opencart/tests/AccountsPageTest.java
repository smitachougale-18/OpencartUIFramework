package com.qa.opencart.tests;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;



public class AccountsPageTest extends BaseTest {
	
	@BeforeClass    // precondition for account page user should be logged in
	
	public void accSetup() {
		
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test (priority =1)
	
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test (priority=2)
	
	public void accPageUrlTest() {
		
		Assert.assertTrue(accPage.getAccPageUrl());
		
		}
	
    @Test (priority=3)
 
   public void logoutLinkExistTest() {
	Assert.assertTrue(accPage.isLogoutLinkExist());
       }
    
    @Test (priority=4)
    
    public void searchExistTest() {
    	Assert.assertTrue(accPage.isSearchExist());
    }
	
	
	@Test(priority=5)
	
	public void accSecHeadersListTest() {
		ArrayList<String> actHeadersList= accPage.getAccSecHeadersList();
		System.out.println("The actual headers list : " + actHeadersList);
		
		Assert.assertEquals(actHeadersList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);   //use assert of collection because ArrayList is nothing but collection
	}
	
	
	@DataProvider 
	
	public Object [] [] getProductKey(){
		return new Object[] [] {
			
			{"Macbook"},
			{"imac"},
			{"Samsung"},
			};
		
	}
	
	
	@Test (dataProvider= "getProductKey", priority=6)
	public void searchCheckTest(String productKey) {
// performSerach method return the object of next landing page object , so here 'searchResultPage' reference given, so that we can call the methods of SearchResultPage
		
		searchResultPage= accPage.performSearch(productKey);
		
		Assert.assertTrue(searchResultPage.isSerachSuccessful());
		
	}
	
	
	@DataProvider 
	
	public Object [] [] getProductData(){
		return new Object[] [] {
			
			{"Macbook" ,"MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"imac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"}
			
			
		};
		
	}
	
	@Test (dataProvider= "getProductData", priority=7)
	public void SearchTest(String searchKey, String mainProductName ) {
	
	searchResultPage= accPage.performSearch(searchKey);
	
	if (searchResultPage.isSerachSuccessful())
	{
		
		// selectProduct method return the object of next landing page object , so here 'productInfoPage' reference given, so that we can call the methods of ProductInfoPage
		productInfoPage= searchResultPage.selectProduct(mainProductName);
		
		String actualProductHeader= productInfoPage.getProductHeader(mainProductName);
		
		Assert.assertEquals(actualProductHeader, mainProductName);
	}
	
}

	
	
	
}
