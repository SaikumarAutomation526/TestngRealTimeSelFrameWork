package com.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.utilities.Utility;

public class ProductCatalogue extends Utility{

	public WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css  = ".mb-3")
	List<WebElement> products;

	@FindBy(css=".ng-animating")
	WebElement loading;

	By successMessage=By.cssSelector("#toast-container");
	By addToCart=By.cssSelector("[routerlink*='cart']");

	public void  clickOncartForGivenProduct(String product) {
		for(WebElement ele:products) {
			String productName=  ele.findElement(By.cssSelector(".card-body h5 b")).getText();
			if(productName.equalsIgnoreCase(product)) {
				ele.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;
			}
		}
	}

	public String successMessagewhileAddToCart() {
		String message= waitForElementToAppear(successMessage).getText();
		waitForElementToDisappear(loading);
		return message;

	}
	

}

