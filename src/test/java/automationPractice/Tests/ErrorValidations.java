package automationPractice.Tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import automationPractice.TestComponents.BaseTest;
import automationPractice.TestComponents.Retry;
import automationPractice.pageobject.CartPage;
import automationPractice.pageobject.ProductCatalogue;

public class ErrorValidations extends BaseTest
{
       @Test(groups= {"ErrorValidations"}, retryAnalyzer=Retry.class)
       public void LoginErrorValidation()
       {
    	   String path=System.getProperty("user.dir")+"//reports//index.html";
   		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
   		reporter.config().setReportName("Web Automation Results");
   		reporter.config().setDocumentTitle("Test Results");
   		
   		ExtentReports extent=new ExtentReports();
   		extent.attachReporter(reporter);
   		extent.setSystemInfo("Tester", "sangita");
	       ProductCatalogue pc=l.loginapp("prasadsangita6@gmail.com", "Sangit@1566");
	       //Assert.assertEquals("Incorrect email or password.", l.LoginErrormsg());      
	   }
       @Test
       public void productCatalogueErrorValidation()
       {
    	   String name="ZARA COAT 3";
	       ProductCatalogue pc=l.loginapp("shetty@gmail.com", "Iamking@000");
	       List<WebElement> products=pc.getProductList();
	       pc.getProductByName(name);
	       pc.addToCart(name);
	       CartPage cartpage=pc.goToCartPage();
	       Boolean match =cartpage.verifyCartProduct("Zara coat 33");
	       Assert.assertFalse(match);      
	   }
}
