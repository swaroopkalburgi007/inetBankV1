package com.inetBank.testCases;


import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBank.pageObjects.Loginpage;



public class TC_logintest_01 extends baseClass {
	
	@Test
	
	
	public void loginTest() throws IOException
	{
		
	
	
		Loginpage lp=new Loginpage(driver);
		
		lp.setUserName(username);
		logger.info("username is entered");
		
		lp.setPassword(password);
		logger.info("password is entered");
		
		lp.clickSubmit();
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			
			logger.info("login test pass");
		}
		else
		{
			CaptureScreen (driver,"logintest");
			 Assert.assertTrue(false);
			 logger.info("login test fail");
		}
	}

}
