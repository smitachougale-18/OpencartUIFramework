package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
    private WebDriver driver; 
	
	private ElementUtil eleUtil;
	
	private Map<String, String> productInfoMap;
	
	private By productImage= By.cssSelector("ul.thumbnails img");
	private By productMetaData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
		
		
	}
	
	public String getProductHeader(String mainProductName) {
		
		String xpath= "//h1[text()='"+mainProductName+"']";
		String productHeader= eleUtil.doGetText(By.xpath(xpath));
		return productHeader;
	}
	
	
	public int getProductImageCount() {
	
		return eleUtil.waitForElementsToBeVisible(productImage, AppConstants.LARGE_TIME_OUT).size();
		}
	
	
     public String getProductPageTitle(String productTitle) {
    	 
    	return eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, productTitle);
    	 
     }
	
	public String getProductPageUrl(String searchKey) {
		return eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, searchKey );
	}
	
	//Brand: Apple
	//Product Code: Product 18
	//Reward Points: 800
	//Availability: In Stock
	
	public Map<String, String> getProductMetaData() {
		
		List<WebElement> metaList= eleUtil.getElements(productMetaData);
		productInfoMap = new HashMap<String, String>();
		
		for(WebElement e: metaList) {                      // used for loop to capture all text of metalist
			String metaText= e.getText();
			
			String meta[]= metaText.split(":")	;                              // split method is used to seperate key and value on the basis on :
			
			String metaKey= meta[0].trim();
			
			String metaValue = meta[1].trim();
		    System.out.println(metaKey +" "+metaValue);
			productInfoMap.put(metaKey, metaValue);
		}
		
		productInfoMap.forEach((k,v) -> System.out.println(k+ ":" +v));
		
		return productInfoMap;
		
		
	}
	
	
	
	

}
