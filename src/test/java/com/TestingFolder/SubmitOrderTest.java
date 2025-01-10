package com.TestingFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageObject.CheckoutPage;
import com.pageObject.ConformationPage;
import com.pageObject.LandingPage;
import com.pageObject.OrderPage;
import com.pageObject.CartPage;
import com.pageObject.ProductCatalogue;
import com.testComponents.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productNameToCart="ADIDAS ORIGINAL";
    @Test(dataProvider ="dataset",groups= {"purchase"})
	public  void submitOrder(HashMap<String, String> input) throws FileNotFoundException  {
		
		ProductCatalogue productCatalogue=landingPage.loginPage(input.get("email"),input.get("password"));
		productCatalogue.clickOncartForGivenProduct(input.get("product"));
		String successMessage = productCatalogue.successMessagewhileAddToCart();
		System.out.println(successMessage);
		CheckoutPage checkoutPage=productCatalogue.clickOnCart();
		boolean verify= checkoutPage.clickOnCheckOut(input.get("product"));
		Assert.assertTrue(verify,"true");
		CartPage orderpage =checkoutPage.checkOutButton();
		orderpage.selectCountry("India");
		ConformationPage confirmpPage= orderpage.placeOrder();
		String str=confirmpPage.getMessage();
		Assert.assertEquals(str, "THANKYOU FOR THE ORDER.");
	   

	}
    @Test(dependsOnMethods ="submitOrder" )
    public void orderHistory() {
    
    	ProductCatalogue productCatalogue=landingPage.loginPage("raghu123456@gmail.com", "Saikumar@26");
    	OrderPage orderPage =productCatalogue.clickOnOrdersPage();
    	boolean status= orderPage.verifyProductName(productNameToCart);
    	Assert.assertTrue(status);

    	
    }
    

//    @DataProvider(name="dataset")
//    public Object[][] getData() {
//    	HashMap<String, String> data=new HashMap<String, String>();
//    	data.put("email","raghu123456@gmail.com");
//    	data.put("password","Saikumar@26");
//    	data.put("product", "ADIDAS ORIGINAL");
//    	HashMap<String, String> data1=new HashMap<String, String>();
//    	data1.put("email","anshika@gmail.com");
//    	data1.put("password","Iamking@000");
//    	data1.put("product", "IPHONE 13 PRO");
//    	Object[][] onj=new Object[][] {
//    		{data},
//    		{data1}
//    	};
//    	return onj;
//    }
    
//    @DataProvider(name="dataset")
//    public Object[][] getData() {
//    	HashMap<String, String> data=new HashMap<String, String>();
//    	data.put("email","raghu123456@gmail.com");
//    	data.put("password","Saikumar@26");
//    	data.put("product", "ADIDAS ORIGINAL");
//    	HashMap<String, String> data1=new HashMap<String, String>();
//    	data1.put("email","anshika@gmail.com");
//    	data1.put("password","Iamking@000");
//    	data1.put("product", "IPHONE 13 PRO");
//    	Object[][] onj=new Object[][] {
//    		{data},
//    		{data1}
//    	};
//    	return onj;
//    }
    @DataProvider(name="dataset")
    public Object[][] getData() throws IOException {
    	List<HashMap<String, String>> list=getJsonData(System.getProperty("user.dir")+"/src/test/java/com/data/PurchaseOrder.json");
    	Object[][] onj=new Object[][] {
    		{list.get(0)},
    		{list.get(1)}
    	};
    	return onj;
    }
    

}
