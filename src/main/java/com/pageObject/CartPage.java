package com.pageObject;

import java.awt.print.PageFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.Utility;

public class CartPage extends Utility{
	public	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results button")
	List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public void selectCountry(String countryName) {
		Actions action =new Actions(driver);
		action.sendKeys(country,countryName).perform();
		for(WebElement ele:countries) {
			String expected=ele.findElement(By.tagName("span")).getText();
			if(expected.equalsIgnoreCase(countryName)) {
				System.out.println(expected);
				ele.click();
				break;
			}
		}
	}
	public ConformationPage placeOrder() {
		submit.click();
		return new ConformationPage(driver);
	}

}
