package stepDefinations;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import automationPractice.TestComponents.BaseTest;
import automationPractice.pageobject.CartPage;
import automationPractice.pageobject.CheckoutPage;
import automationPractice.pageobject.ConfirmationPage;
import automationPractice.pageobject.Landingpage;
import automationPractice.pageobject.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImpl extends BaseTest {
	public Landingpage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartpage;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
		//code
	}
	
	@Given ("^Logged in with email (.+) and password (.+)$")
	public void logged_in_with_email_and_password(String email,String password)
	{
		productCatalogue=landingPage.loginapp(email,password);
	}
	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addToCart(productName);
	}
	@When ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		cartpage = productCatalogue.goToCartPage();
		Boolean match=cartpage.verifyCartProduct(productName);
		Assert.assertTrue(match);
		checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		confirmationpage=checkoutpage.SubmitOrder();
	}
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string)
	{
		String confirmmessage = confirmationpage.getConfirmationmsg();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
		driver.close();
	}
}
