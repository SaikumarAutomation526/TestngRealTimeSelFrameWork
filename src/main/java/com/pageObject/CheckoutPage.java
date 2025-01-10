package com.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.fasterxml.jackson.databind.node.BooleanNode;
import com.utilities.Utility;

public class CheckoutPage extends Utility {
	public	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	@FindBy(css =".cartSection h3")
	List<WebElement> item;
	
	@FindBy(css = ".subtotal ul li button")
	WebElement checkOut;
	
	
	public boolean clickOnCheckOut(String productNameToCart) {
		boolean verifyProduct=false;
		for(WebElement ele:item) {
			String expectProduct= ele.getText();
			if(expectProduct.equalsIgnoreCase(productNameToCart)) {
				System.out.println("successfully added this item ");
				verifyProduct=true;
				break;
			}
		}
		return verifyProduct;
		
	}
	
	public CartPage checkOutButton() {
		checkOut.click();
		CartPage orderpage=new CartPage(driver);
		return orderpage;
	}


}
