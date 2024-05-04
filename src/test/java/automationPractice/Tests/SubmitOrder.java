package automationPractice.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationPractice.TestComponents.BaseTest;
import automationPractice.pageobject.CartPage;
import automationPractice.pageobject.CheckoutPage;
import automationPractice.pageobject.ConfirmationPage;
import automationPractice.pageobject.OrderPage;
import automationPractice.pageobject.ProductCatalogue;

public class SubmitOrder extends BaseTest {
	      String name="ZARA COAT 3";
	          
           @Test(dataProvider="getdata",groups= {"Purchase"})
           public void submitOrder(HashMap<String,String>input) throws InterruptedException, IOException
           {
	       String countryname="India";
	       ProductCatalogue pc=l.loginapp(input.get("email"),input.get("password"));
	       List<WebElement> products=pc.getProductList();
	       //pc.getProductByName(input.get("product"));
	       pc.addToCart(input.get("productname"));
	       CartPage cartpage=pc.goToCartPage();
	       Boolean match =cartpage.verifyCartProduct(input.get("productname"));
	       Assert.assertTrue(match);
	       CheckoutPage cp=cartpage.goToCheckout();
	       cp.selectCountry(countryname);
	       ConfirmationPage comp=cp.SubmitOrder();
	       String comfirmmsg = comp.getConfirmationmsg();
	       Assert.assertTrue(comfirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
           }
     @Test(dependsOnMethods={"submitOrder"})
     public void OrderHistoryTest() throws InterruptedException
     {
    	 ProductCatalogue pc=l.loginapp("prasadsangita6@gmail.com", "Sangita@1566");
	     OrderPage op=pc.goToOrderHistoryPage();
	     Assert.assertTrue(op.VerifyOrderDisplay(name));
     }
    
     @DataProvider
     public Object[][] getdata() throws IOException
     {
//    	 HashMap<String,String> map=new HashMap<String,String>();
//    	 map.put("email", "prasadsangita6@gmail.com");
//    	 map.put("pass", "Sangita@1566");
//    	 map.put("Product", "ZARA COAT 3");
//    	 HashMap<Object,Object> map1=new HashMap<Object,Object>();
//    	 map1.put("email", "shetty@gmail.com");
//    	 map1.put("pass", "Iamking@000");
//    	 map1.put("Product", "IPHONE 13 PRO");
    	
//    	 return new Object[][] {{map},
//    		 {map1}};
    	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//automation//data//purchaseorder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    	//List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//automation//data//purchaseorder.json");
     	//return new Object[][] {{"prasadsangita6@gmail.com","Sangita@1566","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","IPHONE 13 PRO"}};
     }
     
}
