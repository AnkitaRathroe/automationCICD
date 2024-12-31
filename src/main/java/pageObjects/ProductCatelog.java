package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import AbstractComponent.BaseClass;

public class ProductCatelog extends BaseClass {

	WebDriver driver;

	public ProductCatelog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".ng-animating")
	WebElement animation;

	@FindBy(css = ".mb-3")
	List<WebElement> catogList;

	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;

	By productList = By.cssSelector(".mb-3");

	By clickProduct = By.cssSelector(".card-body button:last-of-type");

	By toast = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForAppear(productList);

		return catogList;

	}

	public WebElement getProductName(String product) {

		WebElement productName = getProductList().stream()
				.filter(productname -> productname.findElement(By.cssSelector("b")).getText().equals("QWERTY"))
				.findFirst().orElse(null);
		return productName;

	}

	public void addProducttoCart(String productName) {

		WebElement prod = getProductName(productName);
		prod.findElement(clickProduct).click();
		waitForAppear(toast);
		waitforDisAppear(animation);

	}

	public void clickOnAddToCart() {
		cartBtn.click();

	}

}
