package com.TestingFolder;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandLoneTest {

	public static void main(String[] args) {
		String productNameToCart="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("raghu123456@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Saikumar@26");
		driver.findElement(By.id("login")).click();
		List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		for(WebElement ele:products) {
			String productName=  ele.findElement(By.cssSelector(".card-body h5 b")).getText();
			if(productName.equalsIgnoreCase(productNameToCart)) {
				System.out.println(productName);
				ele.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;
			}
		}

		String succesfullMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))).getText();
		System.out.println(succesfullMessage);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement( By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']"))).click();
		List<WebElement> items= driver.findElements(By.cssSelector(".cartSection h3"));
		for(WebElement ele:items) {
			String expectProduct= ele.getText();
			if(expectProduct.equalsIgnoreCase(productNameToCart)) {
				System.out.println("successfully added this item ");
				break;
			}
		}
		driver.findElement(By.cssSelector(".subtotal ul li button")).click();
		Actions action =new Actions(driver);
		String country="India";
		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),country).perform();
		List<WebElement> countries=   driver.findElements(By.cssSelector(".ta-results button"));
		for(WebElement ele:countries) {
			String expected=ele.findElement(By.tagName("span")).getText();
			if(expected.equalsIgnoreCase(country)) {
				System.out.println(expected);
				ele.click();
				break;
			}
		}
		driver.findElement(By.cssSelector(".action__submit")).click();
		String str= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(str, "THANKYOU FOR THE ORDER.");
		driver.close();

	}

}
