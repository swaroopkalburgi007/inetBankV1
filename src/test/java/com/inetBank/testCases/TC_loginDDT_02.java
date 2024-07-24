package com.inetBank.testCases;

import java.io.IOException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.inetBank.pageObjects.Loginpage;
import com.inetBank.utilities.ExtentManager;
import com.inetBank.utilities.XLUtils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;





public class TC_loginDDT_02 extends baseClass{

	public String username="";
	public String password="";
	public String usern="";
	public String passw="";
	
	XLUtils u= new XLUtils();
	  ExtentTest test = ExtentManager.getExtentTest();

	  @Test(dataProvider = "loginDataProvider")
	public void LoginDDT(String username,String password) throws InterruptedException, IOException
	{
		 
	
		 if (test != null) 
		 {
	            test.log(Status.INFO, "Executing testMethod1");
	        }
			Loginpage lp= new Loginpage(driver);
			lp.setUserName(username);
			//logger.info("User Name provided");
			lp.setPassword(password);
			//logger.info("PWD provided");
			lp.clickSubmit();
	
			
			Thread.sleep(3000);
			
			if(IsAlertPresent()==true)
			{
				
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				Assert.assertTrue(false);
				logger.warn("Login Failed");
			}
			else
			{
				Assert.assertTrue(true);
				logger.info("Login passed");
				lp.clicklogout();
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
			}
			
			}
	  public boolean IsAlertPresent()
		{  
			try
			{
				driver.switchTo().alert();
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		
		@DataProvider(name = "loginDataProvider")
	    public Object[][] loginDataProvider() throws InvalidFormatException, IOException
		{
			String path= "C:\\Users\\swaro\\inetBankV1\\src\\test\\java\\com\\inetBank\\testData\\LoginData.xls";
			String username =u.getData(path,"Sheet1", "username",1 );
			System.out.println(username);
			String password =u.getData(path,"Sheet1", "password",1 );
			System.out.println(password); 
			
			String usern =u.getData(path,"Sheet1", "username",2 );
			System.out.println(usern);
			String passw =u.getData(path,"Sheet1", "password",2 );
			System.out.println(passw); 
	      
			
			return new Object[][] 
	       {
	            {username, password},
	            {usern, passw}
	            
	       };
		}
}

