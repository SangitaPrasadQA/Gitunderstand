package automationPractice.pageobject;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationPractice.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
       this.driver=driver;
       PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement successmsg;
	
	By productsBy =By.cssSelector(".mb-3");
	By productText=By.cssSelector("b");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastBy=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName)
	{
		WebElement prod=products.stream().filter(product->
		product.findElement(productText).getText().equals(productName)).findFirst().orElse(null);
	  return prod;
	}
	public void addToCart(String productName)
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastBy);
		waitForElementToDisappear(successmsg);
	}
	
	
}

