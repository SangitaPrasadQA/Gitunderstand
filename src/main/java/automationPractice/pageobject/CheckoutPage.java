package automationPractice.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationPractice.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
	       this.driver=driver;
	       PageFactory.initElements(driver,this);
	}
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement placeOrder;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryname) 
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,countryname).build().perform();
		waitForElementToAppear(results);
		a.moveToElement(selectCountry).click().build().perform();
	}
   public ConfirmationPage SubmitOrder() throws InterruptedException
   {
	   JavascriptExecutor js=(JavascriptExecutor)driver;
	   js.executeScript("window.scrollBy(0,500)");
       js.executeScript("arguments[0].click();",placeOrder);
       //submit.click();
       Thread.sleep(2000);     
    return new ConfirmationPage(driver);
   }
}
