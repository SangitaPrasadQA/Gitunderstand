package automationPractice.pageobject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import automationPractice.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
       this.driver=driver;
       PageFactory.initElements(driver,this);
	}
	@FindBy(css="div[class='cartSection'] h3")
	List<WebElement> cartitems;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkout;
	public boolean verifyCartProduct(String productName)
	{
		waitForAllElementsToAppear(cartitems);
        boolean match=cartitems.stream().anyMatch(item->item.getText().equals(productName));
        
        return match;
	}
    
    public CheckoutPage goToCheckout()
    {
       checkout.click();
       return new CheckoutPage(driver);
    }
}

