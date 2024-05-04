package automationPractice.pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationPractice.AbstractComponent.AbstractComponent;

public class Landingpage extends AbstractComponent{

	WebDriver driver;
	public Landingpage(WebDriver driver) 
	{
		super(driver);
       this.driver=driver;
       PageFactory.initElements(driver,this);
	}
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPsw;
	@FindBy(id="login")
	WebElement login;
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
    public ProductCatalogue loginapp(String email,String password)
    {
    	userEmail.sendKeys(email);
	    userPsw.sendKeys(password);
	    login.click();
	    return new ProductCatalogue(driver);
    }
    
    public String LoginErrormsg()
    {
    	waitForElementToAppear(errormsg);
    	return errormsg.getText();
    }
    public void goTo()
    {
    	driver.get("https://rahulshettyacademy.com/client/");
    }
}
