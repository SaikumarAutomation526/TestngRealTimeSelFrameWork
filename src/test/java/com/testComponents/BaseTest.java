package com.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pageObject.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver intializeBroswer() throws FileNotFoundException {
		
		FileInputStream fs=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/com/resources/GlobalData.properties"));
		Properties p=new Properties();
		try {
			p.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String broswer=p.getProperty("browser1");
      
		if(broswer.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();

		}
		else if(broswer.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(broswer.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws FileNotFoundException {
		driver=intializeBroswer();
	    landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void close() {
		driver.close();
	}
	@SuppressWarnings("deprecation")
	public List<HashMap<String,String>> getJsonData(String path) throws IOException {
		//read json to string
	
	String stjsonContent=	FileUtils.readFileToString(new File(path));
	// string to hashmap jackson databind
	ObjectMapper mapper=new  ObjectMapper();
	List<HashMap<String, String>> data=mapper.readValue(stjsonContent, new TypeReference<List<HashMap<String, String>>>() {
	});
	return data;
	}
	public String getScreenshot(String testcaseName,WebDriver driver) {
	      File	srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	      File desti=new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
	      try {
	      FileUtils.copyFile(srcFile, desti);
	      }
	      catch (Exception e) {
			System.out.println(e);
		}
	      return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	    }

}
