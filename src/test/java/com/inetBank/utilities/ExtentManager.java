package com.inetBank.utilities;



import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentManager {
	
	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    public static ExtentReports getInstance() {
        if (extent == null) 
        {
        	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
            createInstance("test-output/extent.html");
        }
        return extent;
    }

    
    public static ExtentReports createInstance(String repName)
    {
    	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String repName1="Test-Report-"+timeStamp+".html";
    	
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName1);
        htmlReporter.loadXMLConfig("E:\\Learning\\GoAutoBanking\\extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));

        return extent;
    }

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
   

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static ExtentTest getExtentTest()
    {
        return extentTest.get();
        }
	

}