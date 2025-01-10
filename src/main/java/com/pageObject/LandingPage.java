package com.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilities.Utility;

public class LandingPage extends Utility{

	public WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement useremailInputBox;

	@FindBy(id="userPassword")
	WebElement passwordInputBox;

	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[role='alert']")
	WebElement error;

    public ProductCatalogue loginPage(String email,String password) {
    	useremailInputBox.sendKeys(email);
    	passwordInputBox.sendKeys(password);
    	submit.click();
    	ProductCatalogue productCatalogue=new ProductCatalogue(driver);
    	return productCatalogue;
    }
    public void goTo() {
    	driver.get("https://rahulshettyacademy.com/client/");
    }
    public String errorMessage() {
    	waitForElementToAppear(error);
      return	error.getText();
    }




}
