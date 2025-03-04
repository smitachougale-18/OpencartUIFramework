package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	WebDriver driver; 
	
	ElementUtil eleUtil;
	
	
	By productSearchLayout = By.cssSelector("div.product-layout");
	
	
	
	public SearchResultPage(WebDriver driver) {
		
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
		
	}
	
	public boolean isSerachSuccessful() {
		
		List<WebElement> searchList= eleUtil.waitForElementsToBeVisible(productSearchLayout, AppConstants.LARGE_TIME_OUT);
		
		if (searchList.size()>0) {
			
			System.out.println("The search is successfully done ......");
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		
		By mainProdName = By.linkText(mainProductName);
		eleUtil.doClick(mainProdName);
		return new ProductInfoPage(driver);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
