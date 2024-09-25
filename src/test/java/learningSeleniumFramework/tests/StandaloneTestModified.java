package learningSeleniumFramework.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import learningSeleniumFramework.pageobjects.CartPage;
import learningSeleniumFramework.pageobjects.CheckoutPage;
import learningSeleniumFramework.pageobjects.ConfirmationPage;
import learningSeleniumFramework.pageobjects.OrderPage;
import learningSeleniumFramework.pageobjects.ProductCatalogue;
import learningSeleniumFramework.testcomponents.BaseTest;

public class StandaloneTestModified extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String,String> map) throws IOException, InterruptedException {
		ProductCatalogue pc = lp.loginApplication(map.get("username"), map.get("password"));
		pc.addProductToCart(map.get("productName"));
		CartPage cartPage = pc.goToCart();
		Assert.assertTrue(cartPage.isProductExistInCart(map.get("productName")));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		Assert.assertTrue(confirmationPage.IsConfirmationMatched("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods={"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue pc = lp.loginApplication("piyush@rahulshettyacademy.com", "Welcome#123");
		OrderPage op = pc.goToOrderPage();
		op.isProductExistOnOrderPage(productName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//learningSeleniumFramework//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
		/*
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("username", "anshika@gmail.com");
		map.put("password", "Iamking@000");
		map.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1 = new HashMap<String, String>();
		map1.put("username", "piyush@rahulshettyacademy.com");
		map1.put("password", "Welcome#123");
		map1.put("productName", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};
		*/
	}
}