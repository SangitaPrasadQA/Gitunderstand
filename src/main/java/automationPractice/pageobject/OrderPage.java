package automationPractice.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationPractice.AbstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
       this.driver=driver;
       PageFactory.initElements(driver,this);
	}
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productsname;
	
	public Boolean VerifyOrderDisplay(String name) throws InterruptedException
	{
		waitForAllElementsToAppear(productsname);
        Boolean match=productsname.stream().anyMatch(item->item.getText().equalsIgnoreCase(name));
        Thread.sleep(2000);
        return match;
	}
}
