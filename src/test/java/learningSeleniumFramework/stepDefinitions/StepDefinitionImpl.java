package learningSeleniumFramework.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import learningSeleniumFramework.pageobjects.CartPage;
import learningSeleniumFramework.pageobjects.CheckoutPage;
import learningSeleniumFramework.pageobjects.ConfirmationPage;
import learningSeleniumFramework.pageobjects.LandingPage;
import learningSeleniumFramework.pageobjects.ProductCatalogue;
import learningSeleniumFramework.testcomponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage lp;
	public ProductCatalogue pc;
	public ConfirmationPage confirmationPage ;
	@Given("I Landed On EcommercePage")
	public void I_Landed_On_EcommercePage() throws IOException {
		lp = launchApplication();
	}
	
	@Given("^Logged In With Username (.+) and Password (.+)$")
	public void Logged_In_With_Username_and_Password(String username,String password) {
		pc = lp.loginApplication(username, password);
	}
	
	@When("^I Add Product (.+) To Cart$")
	public void I_Add_Product_To_Cart(String productName) throws InterruptedException {
		pc.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and Submit The Order$")  //@When can also be used here but if @And is used then it considers the previous annotation for its association
	public void Checkout_and_Submit_The_Order(String productName) {
		CartPage cartPage = pc.goToCart();
		Assert.assertTrue(cartPage.isProductExistInCart(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} Message Is Displayed On ConfirmationPage")
	public void Message_Is_Displayed_On_ConfirmationPage(String message){
		Assert.assertTrue(confirmationPage.IsConfirmationMatched(message));
		driver.close();
	}
	
	@Then("{string} Message Is Displayed")
	public void Message_Is_Displayed(String message){
		Assert.assertEquals(lp.getErrorMessgae(), message);
		driver.close();
	}
			

}
