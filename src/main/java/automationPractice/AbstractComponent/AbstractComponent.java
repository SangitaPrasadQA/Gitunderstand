package automationPractice.AbstractComponent;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automationPractice.pageobject.CartPage;
import automationPractice.pageobject.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		 wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	@FindBy(css="button[class='btn btn-custom'] i[class='fa fa-shopping-cart']")
	WebElement cartButton;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement OrderHistoryPage;	
	public CartPage goToCartPage()
	{
		cartButton.click();
		return new CartPage(driver);
	}
	public OrderPage goToOrderHistoryPage()
	{
		waitForElementToAppear(OrderHistoryPage);
		OrderHistoryPage.click();
		return new OrderPage(driver);
	}
	public void waitForElementToAppear(By findBy)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToAppear(WebElement ele1)
	{
		wait.until(ExpectedConditions.visibilityOf(ele1));
	}
	public void waitForAllElementsToAppear(List<WebElement> ele2)
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(ele2));
	}
	public void waitForElementToDisappear(WebElement ele)
	{
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
    
}
