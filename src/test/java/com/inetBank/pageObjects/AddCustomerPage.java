package com.inetBank.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class AddCustomerPage {
	
WebDriver ldriver;
	
	public AddCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	
	@FindBy(how=How.XPATH, using="//a[.='New Customer']")
	WebElement lnkAddNewCustomer;
	
	@FindBy(how=How.XPATH, using="//input[@name='name']")
	WebElement CustomerTextBox;
	
	@FindBy(how=How.XPATH, using="//tbody/tr[5]/td[2]")
	WebElement rgender;
	
	@FindBy(how=How.XPATH, using="//input[@id='dob']")
	WebElement Customerdob;
	
	@FindBy(how=How.XPATH, using="//textarea[@name='addr']")
	WebElement CustomerAddress;
	
	@FindBy(how=How.XPATH, using="//input[@name='city']")
	WebElement CityTextBox;
	
	@FindBy(how=How.XPATH, using="//input[@name='state']")
	WebElement StateTextBox;
	
	@FindBy(how=How.XPATH, using="//input[@name='pinno']")
	WebElement pincode;
	
	@FindBy(how=How.XPATH, using="//input[@name='telephoneno']")
WebElement Mobnum;
	
	@FindBy(how=How.XPATH, using="//input[@name='emailid']")
	WebElement cmailid;
	
	@FindBy(how=How.XPATH, using="//input[@name='password']")
	WebElement custpwd;
	
	@FindBy(how=How.XPATH, using="//input[@name='sub']")
	WebElement custsubmit;
	
	public void ClickAddNewCustomer()
	{
		lnkAddNewCustomer.click();
	}
	
	public void CustomerTBox(String cname)
	{
		CustomerTextBox.sendKeys(cname);
	}
	public void SelectGender(String cgender)
	{
		rgender.click();
	}
	public void SelectCustDOB(String mm,String dd,String yy)
	{
		Customerdob.sendKeys(mm);
		Customerdob.sendKeys(dd);
		Customerdob.sendKeys(yy);
	}
	public void CustAddrss(String caddress)
	{
		CustomerAddress.sendKeys(caddress);
	}
	public void SelectCity(String ccity )
	{
		CityTextBox.sendKeys(ccity);
	}
	public void SelectState(String cstate)
	{
		StateTextBox.sendKeys(cstate);
	}
	public void SelectPincode(String cpin)
	{
		pincode.sendKeys(String.valueOf(cpin));
	}
	public void SelectMobiNo(String cmob)
	{
		Mobnum.sendKeys(cmob);
	}
	public void CustMailid(String cemail)
	{
		cmailid.sendKeys(cemail);
	}
	public void CustPassword(String cpaswd)
	{
		custpwd.sendKeys(cpaswd);
	}
	public void ClickSubmitBtn()
	{
		custsubmit.click();
	}


}
