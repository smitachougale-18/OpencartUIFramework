package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

//class ProductInfoPage

public class ProductPageTest extends BaseTest {
	
	@BeforeClass
	
	public void prodInfoSetup() {
		
	accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	
	// accPage reference because doLogin method return the AccountPage object, so AccountPage object ref variable accPage gave
		
	}
	
	@Test
	public void productHeaderTest() {
		 
		searchResultPage= accPage.performSearch("MacBook");
		// searchResultPage reference because performSearch method return the SearchResultPage object, so searchResultPage object ref variable searchResultPage gave
		
		productInfoPage= searchResultPage.selectProduct("MacBook Pro");
		
		String actProdHeader= productInfoPage.getProductHeader("MacBook Pro");
		
		Assert.assertEquals(actProdHeader, "MacBook Pro");
		
	}
	
@DataProvider 
	
	public Object [] [] getProductInfoData(){
		return new Object[] [] {
			
			{"Macbook" ,"MacBook Pro", AppConstants.MACBOOK_PRO_IMAGES_COUNT},
			{"Macbook", "MacBook Air", AppConstants.MACBOOK_AIR_IMAGES_COUNT},
			{"imac", "iMac", AppConstants.IMAC_IMAGES_COUNT},
			//{"Samsung", "Samsung SyncMaster 941BW"},
			//{"Samsung", "Samsung Galaxy Tab 10.1"}
			
			
		};
		
	}
	
	
	
	@Test (dataProvider="getProductInfoData")
	
	public void productImageCountTest(String searchKey, String mainProductName, int imageCount) {
		
		searchResultPage= accPage.performSearch (searchKey);          //("MacBook")
		
		productInfoPage= searchResultPage.selectProduct(mainProductName);            // "MacBook Pro"
		
		int actProductImage= productInfoPage.getProductImageCount();
		
		System.out.println("The actual product image count is : " + actProductImage);
		
		Assert.assertEquals(actProductImage, imageCount);
	}
	
	@Test
	
	public void productPageTitleTest() {
		
        searchResultPage= accPage.performSearch("MacBook");
		
		productInfoPage= searchResultPage.selectProduct("MacBook Pro");
		
		//String actProductPageTitle =productInfoPage.getProductPageTitle();
		
	}
	
	
	@Test
	
	public void productMetaDataTest() {
		
		    searchResultPage= accPage.performSearch("MacBook");
			
			productInfoPage= searchResultPage.selectProduct("MacBook Pro");	
		
		Map<String, String> actMetaDataMap=	productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
	}
	
	
	

}
