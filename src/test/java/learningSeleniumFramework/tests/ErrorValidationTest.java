package learningSeleniumFramework.tests;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import learningSeleniumFramework.pageobjects.CartPage;
import learningSeleniumFramework.pageobjects.ProductCatalogue;
import learningSeleniumFramework.testcomponents.BaseTest;
import learningSeleniumFramework.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		lp.loginApplication("piyush@rahulshettyacademy.com", "Welcome#1234");
		Assert.assertEquals(lp.getErrorMessgae(), "Incorrect email or password.");
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue pc = lp.loginApplication("piyush@rahulshettyacademy.com", "Welcome#123");
		pc.addProductToCart(productName);
		CartPage cartPage = pc.goToCart();
		Assert.assertFalse(cartPage.isProductExistInCart("ZARA COAT 33"));
	}
}