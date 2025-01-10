package com.TestingFolder;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.pageObject.CheckoutPage;
import com.pageObject.ConformationPage;
import com.pageObject.LandingPage;
import com.pageObject.CartPage;
import com.pageObject.ProductCatalogue;
import com.testComponents.BaseTest;
import com.testComponents.Retry;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidation extends BaseTest {
    @Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public  void loginErrorValidation() throws FileNotFoundException  {
		String productNameToCart="ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingPage.loginPage("raghu12346@g", "Saikumar@23339");
		String errorvalidation=landingPage.errorMessage();
		Assert.assertEquals(errorvalidation,"Incorrect email or password.er");
		

	}
    @Test
   	public  void productErrorvalidation() throws FileNotFoundException  {
   		String productNameToCart="ADIDAS ORIGINAL";
   		ProductCatalogue productCatalogue=landingPage.loginPage("raghu123456@gmail.com", "Saikumar@26");
   		productCatalogue.clickOncartForGivenProduct(productNameToCart);
   		String successMessage = productCatalogue.successMessagewhileAddToCart();
   		System.out.println(successMessage);
   		CheckoutPage checkoutPage=productCatalogue.clickOnCart();
   		boolean verify= checkoutPage.clickOnCheckOut("ADIDAS ORIGINAL 12");
   		Assert.assertFalse(verify);
   		
   	   

   	}

}
