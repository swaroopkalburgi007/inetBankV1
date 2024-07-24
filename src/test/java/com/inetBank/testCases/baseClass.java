package com.inetBank.testCases;

import java.io.File;
import java.io.IOException;
//import java.time.Duration;

import org.apache.commons.io.FileUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBank.utilities.ReadConfig;



public class baseClass {
	
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	
	public void setup(String br)
	{
		
		 logger=Logger.getLogger("eBanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if (br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver=new ChromeDriver();
		}
		
		else if (br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readconfig.getIEPath());
			driver=new EdgeDriver();
		
		}
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
	}
		
	@AfterClass
		public void tearDown()
		{
			driver.quit();
		}
	
	public void CaptureScreen(WebDriver driver, String Tname) throws IOException
    {
    	TakesScreenshot ts= (TakesScreenshot) driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	  File target =new File(System.getProperty("user.dir")+"/Screenshot/"+ "tname" +".png");
    	  FileUtils.copyFile(source, target);
    	  System.out.println("Screenshot taken");
    	  }
	
   

}