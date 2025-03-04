package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	
	ElementUtil eleUtil;
	
	private By logoutLink= By.linkText("Logout");
	private By search= By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accSecHeader = By.cssSelector("div#content h2");
	
	
	public AccountsPage (WebDriver driver) {
		this.driver=driver;
		
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getAccPageTitle() {
		
	String title=eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCOUNT_PAGE_TITLE);
		
	    //String title =driver.getTitle();
	
		System.out.println("The Account page title is :" +title);
		return title;
	}
	
	public boolean getAccPageUrl() {
		
		String url= eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT,AppConstants.ACCOUNT_PAGE_URL_PARAM);
	
		
		//String url =driver.getCurrentUrl();
		
		System.out.println("The Account page url is :" +url);
		if (url.contains(AppConstants.ACCOUNT_PAGE_URL_PARAM)) {
			return true;
		}
		
		return false;
	}
	
	
	public boolean isLogoutLinkExist() {
	
		return eleUtil.doEleIsDisplayed(logoutLink);
		
		//return driver.findElement(logoutLink).isDisplayed();
	}
	
	public boolean isSearchExist() {
		
		return eleUtil.doEleIsDisplayed(search);
		
		//return driver.findElement(search).isDisplayed();
	}
	
	public SearchResultPage performSearch(String productKey) {
		
		if (isSearchExist()) {
			
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultPage(driver);
		}
		
		else {
			System.out.println("Search field is not present on page ....");
			return null;
		}
	}
	
	
	public ArrayList<String> getAccSecHeadersList() {
		
		
		List<WebElement> secList= eleUtil.waitForElementsToBeVisible(accSecHeader, AppConstants.LARGE_TIME_OUT);
		
			//	List<WebElement> secList= driver.findElements(accSecHeader);
		System.out.println("The total section headers are : " + secList.size());
		
		ArrayList<String>actSecTextList= new ArrayList<String>();
		
		for(WebElement e: secList) {
			String text= e.getText();
			actSecTextList.add(text);
		}
		
		return actSecTextList;
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
