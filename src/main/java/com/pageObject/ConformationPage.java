package com.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.Utility;

public class ConformationPage extends Utility {
	public WebDriver driver;
public ConformationPage(WebDriver driver) {
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}

@FindBy(css = ".hero-primary")
WebElement successmessage;

public String getMessage() {
	return successmessage.getText();
}


}
