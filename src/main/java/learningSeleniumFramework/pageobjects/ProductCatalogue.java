package learningSeleniumFramework.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import learningSeleniumFramework.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> items = driver.findElements(By.cssSelector(".col-lg-4"));
	
	@FindBy(css=".col-lg-4")
	List<WebElement> items;
	
	//By.cssSelector(".ng-animating")
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productBy = By.cssSelector(".col-lg-4");
	By productToAdd = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList(){
		waitForElementToAppear(productBy);
		return items;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(item->item.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		getProductByName(productName).findElement(productToAdd).click();
		Thread.sleep(2000);
		//waitForElementToAppear(toastMessage);
		//waitForElementToDisappear(spinner);
		}

}
